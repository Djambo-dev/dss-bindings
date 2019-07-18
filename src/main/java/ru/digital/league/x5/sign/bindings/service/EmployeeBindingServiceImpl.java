package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class EmployeeBindingServiceImpl implements EmployeeBindingService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeBindingServiceImpl.class);

    private final EmployeeBindingRepository employeeBindingRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(EmployeeBindingInfoDto employeeBindingInfo) {

        if (!CollectionUtils.isEmpty(employeeBindingInfo.getEmployeeBindings())) {
            List<EmployeeBindingEntity> employeeBindingEntities = employeeBindingInfo.getEmployeeBindings().stream()
                    .map(bindingDto -> modelMapper.map(bindingDto, EmployeeBindingEntity.class))
                    .collect(Collectors.toList());

            logger.info("Deleting existing employee binding records");

            employeeBindingEntities.forEach(binding ->
                    employeeBindingRepository.deleteAllByCfoIdAndPersonalNumber(binding.getCfoId(), binding.getPersonalNumber()));

            logger.info("Saving employee bindings {} to DB", employeeBindingEntities);

            employeeBindingRepository.saveAll(employeeBindingEntities);
        }
    }
}
