package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.repository.ClusterEmployeeRepository;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeListDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Что такое "пустые" записи?
 * Данные о сотрудниках к нам приходят из IBM MDM. Т.к. поиск привязанных к сотруднику магазинов происходит по его ТН,
 * то наличие ТН в записи о сотруднике обязательно, однако часть таких записей приходит с пустым полями (заполнен только
 * cfo id). Такие записи я называю "пустыми" и до сохранения в БД они не доходят. На момент написания этого комментария
 * таких записей было примерно 30% от общего количества.
 *
 * НОО=Начальник отдела операций
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClusterEmployeeServiceImpl implements ClusterEmployeeService {

    private static final String ROLE_NOO = "Начальник отдела операций";

    private final ClusterEmployeeRepository clusterEmployeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(ClusterEmployeeListDto clusterEmployeeListDto) {
        log.info("Saving cluster employee bindings...: {}", clusterEmployeeListDto);
        if (!CollectionUtils.isEmpty(clusterEmployeeListDto.getClusterEmployeeBindingList())) {
            List<ClusterEmployeeEntity> employeeEntityList = clusterEmployeeListDto.getClusterEmployeeBindingList().stream()
                    .filter(employeeDto -> employeeDto.getPersonalNumber() != null) // фильтруем null записи
                    .filter(employeeDto -> !employeeDto.getPersonalNumber().isEmpty()) // фильтруем "пустые" записи
                    .filter(employeeDto -> ROLE_NOO.equals(employeeDto.getRole())) // оставляем только сотрудников с ролью НОО
                    .map(employeeDto -> modelMapper.map(employeeDto, ClusterEmployeeEntity.class))
                    .collect(Collectors.toList());

            List<String> clusterIdList = employeeEntityList.stream()
                    .map(ClusterEmployeeEntity::getClusterId)
                    .distinct()
                    .collect(Collectors.toList());
            clusterEmployeeRepository.deleteAllByClusterIdIn(clusterIdList);
            log.info("Deleted existing cluster employee ...: {}", clusterIdList);
            employeeEntityList = clusterEmployeeRepository.saveAll(employeeEntityList);
            log.info("Saved employee {} to DB", employeeEntityList);
        }
    }
}
