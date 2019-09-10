package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeBindingEntity;
import ru.digital.league.x5.sign.bindings.db.repository.EmployeeBindingRepository;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeBindingServiceImpl implements EmployeeBindingService {

    private final EmployeeBindingRepository employeeBindingRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(EmployeeBindingInfoDto employeeBindingInfo) {
        log.info("Saving employee bindings...: {}", employeeBindingInfo);
        if (!CollectionUtils.isEmpty(employeeBindingInfo.getEmployeeBindings())) {
            List<EmployeeBindingEntity> employeeBindingEntities = employeeBindingInfo.getEmployeeBindings().stream()
                    .map(bindingDto -> modelMapper.map(bindingDto, EmployeeBindingEntity.class))
                    .collect(Collectors.toList());

            List<String> updatedCfoIds = employeeBindingEntities.stream()
                    .map(EmployeeBindingEntity::getCfoId)
                    .distinct()
                    .collect(Collectors.toList());
            employeeBindingRepository.deleteAllByCfoIdIn(updatedCfoIds);
            log.info("Deleted existing employee binding...: {}", updatedCfoIds);
            employeeBindingEntities = employeeBindingRepository.saveAll(employeeBindingEntities);
            log.info("Saved employee bindings {} to DB", employeeBindingEntities);
        }
    }
}
