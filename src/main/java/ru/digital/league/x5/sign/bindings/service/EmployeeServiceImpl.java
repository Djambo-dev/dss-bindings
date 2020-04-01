package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.repository.EmployeeRepository;
import ru.digital.league.x5.sign.bindings.dto.EmployeeInfoDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(EmployeeInfoDto employeeInfo) {
        log.info("Saving employee bindings...: {}", employeeInfo);
        if (!CollectionUtils.isEmpty(employeeInfo.getEmployeeBindings())) {
            List<EmployeeEntity> employeeEntityList = employeeInfo.getEmployeeBindings().stream()
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
}
