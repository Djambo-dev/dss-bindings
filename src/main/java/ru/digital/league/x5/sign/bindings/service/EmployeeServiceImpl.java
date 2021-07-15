package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.repository.EmployeeRepository;
import ru.digital.league.x5.sign.bindings.dto.EmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeListDto;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Что такое "пустые" записи?
 * Данные о сотрудниках к нам приходят из IBM MDM. Т.к. поиск привязанных к сотруднику магазинов происходит по его ТН,
 * то наличие ТН в записи о сотруднике обязательно, однако часть таких записей приходит с пустым полями (заполнен только
 * cfo id). Такие записи я называю "пустыми" и до сохранения в БД они не доходят. На момент написания этого комментария
 * таких записей было примерно 30% от общего количества.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final static String UPDATED_CFO_IDS = "updatedCfoIds";
    private final static String EMPTY_CFO_IDS = "emptyCfoIds";

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(EmployeeList employeeList) {
        EmployeeListDto employeeListDto = modelMapper.map(employeeList, EmployeeListDto.class);

        if (employeeListDto != null && !CollectionUtils.isEmpty(employeeListDto.getEmployeeBindingList())) {
            log.info("Saving employee bindings...: {}", employeeListDto);

            Predicate<EmployeeDto> notNullContent = employeeDto ->
                    employeeDto.getPersonalNumber() != null && !employeeDto.getPersonalNumber().isEmpty();

            List<EmployeeEntity> employeeEntityList = getEmployeeEntities(employeeListDto, notNullContent);

            HashMap<String, List<String>> cfoIds = getCfoIds(employeeListDto, notNullContent);

            if (cfoIds.get(EMPTY_CFO_IDS).size() > 0) {
                log.info("Mark as \"is_deleted\" employee ...: {} ", cfoIds.get(EMPTY_CFO_IDS));
                employeeRepository.markAsDeletedByCfoId(cfoIds.get(EMPTY_CFO_IDS));
            }

            if(employeeEntityList.size() > 0) {
                employeeRepository.deleteAllByCfoIdIn(cfoIds.get(UPDATED_CFO_IDS));
                log.info("Deleted existing employee ...: {}", cfoIds.get(UPDATED_CFO_IDS));
                employeeEntityList = employeeRepository.saveAll(employeeEntityList);
            }
            log.info("Saved employee {} to DB", employeeEntityList);
        }
    }

    private List<EmployeeEntity> getEmployeeEntities(EmployeeListDto employeeListDto, Predicate<EmployeeDto> notNullContent) {
        return employeeListDto.getEmployeeBindingList().stream()
                .filter(notNullContent)
                .map(employeeDto -> modelMapper.map(employeeDto, EmployeeEntity.class))
                .collect(Collectors.toList());
    }

    private HashMap<String, List<String>> getCfoIds(EmployeeListDto employeeListDto, Predicate<EmployeeDto> notNullContent) {
        List<String> updatedCfoIds = new ArrayList<>();
        List<String> emptyCfoIds   = new ArrayList<>();
        HashMap<String, List<String>> cfoIds = new HashMap<>();

        employeeListDto.getEmployeeBindingList().stream()
                .filter(employeeDto -> {
                    if (notNullContent.test(employeeDto)) {
                        return true;
                    } else {
                        emptyCfoIds.add(employeeDto.getCfoId());
                        return false;
                    }
                })
                .map(EmployeeDto::getCfoId)
                .distinct()
                .forEach(cfoId -> updatedCfoIds.add(cfoId));
        cfoIds.put(UPDATED_CFO_IDS, updatedCfoIds);
        cfoIds.put(EMPTY_CFO_IDS, emptyCfoIds);
        return cfoIds;
    }
}
