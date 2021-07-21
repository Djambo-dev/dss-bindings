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

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Что такое "пустые" записи?
 * Данные о сотрудниках к нам приходят из IBM MDM. Т.к. поиск привязанных к сотруднику магазинов происходит по его ТН,
 * то наличие ТН в записи о сотруднике обязательно, однако часть таких записей приходит с пустым полями (заполнен только
 * MDM_ID - cfo id). Такие записи я называю "пустыми" и до сохранения в БД они не доходят.
 * <p>
 * Вышеописанное раннее должно восприниматься системой, как записи, которые не имеют привязок. Допустим, есть привязка сотрудника
 * к cfo_id XXXXYYYY. Если придет запись cfo_id XXXXYYYY с пустым перечнем привязок сотрудника, то такую запись решено помечать,
 * как "is_deleted" (но не удалять), что позволит оставить функционал по отображению и подписанию закрытых магазинов нетронутым.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

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
            List<String> cfoIds = getCfoIds(employeeListDto, notNullContent);

            if (cfoIds.size() > 0) {
                log.info("Mark as 'is_deleted' employee with CFO_ID: {}", cfoIds);
                employeeRepository.markAsDeletedByCfoId(cfoIds);
            }
            if (employeeEntityList.size() > 0) {
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

    private List<String> getCfoIds(EmployeeListDto employeeListDto, Predicate<EmployeeDto> notNullContent) {
        return employeeListDto.getEmployeeBindingList().stream()
                .map(EmployeeDto::getCfoId)
                .distinct()
                .collect(Collectors.toList());
    }
}
