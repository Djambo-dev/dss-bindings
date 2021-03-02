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

import java.util.List;
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

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(EmployeeListDto employeeInfo) {
        log.info("Saving employee bindings...: {}", employeeInfo);
        if (!CollectionUtils.isEmpty(employeeInfo.getEmployeeBindingList())) {
            List<EmployeeEntity> employeeEntityList = employeeInfo.getEmployeeBindingList().stream()
                    .filter(employeeDto -> employeeDto.getPersonalNumber() != null) // фильтруем null записи
                    .filter(employeeDto -> !employeeDto.getPersonalNumber().isEmpty()) // фильтруем "пустые" записи
                    .map(employeeDto -> modelMapper.map(employeeDto, EmployeeEntity.class))
                    .collect(Collectors.toList());

            List<String> updatedCfoIds = employeeEntityList.stream()
                    .map(EmployeeEntity::getCfoId)
                    .distinct()
                    .collect(Collectors.toList());
            employeeRepository.deleteAllByCfoIdIn(updatedCfoIds);
            log.info("Deleted existing employee ...: {}", updatedCfoIds);
            employeeEntityList = employeeRepository.saveAll(employeeEntityList);
            log.info("Saved employee {} to DB", employeeEntityList);
        }
    }

    @Override
    public EmployeeDto get(String personalNumber) {
        EmployeeEntity employeeEntity = employeeRepository.getByPersonalNumber(personalNumber);
        return modelMapper.map(employeeEntity != null ? employeeEntity : new EmployeeEntity(), EmployeeDto.class);
    }
}
