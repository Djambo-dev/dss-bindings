package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.repository.ClusterEmployeeRepository;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeListDto;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Что такое "пустые" записи?
 * Данные о сотрудниках к нам приходят из IBM MDM. Т.к. поиск привязанных к сотруднику магазинов происходит по его ТН,
 * то наличие ТН в записи о сотруднике обязательно, однако часть таких записей приходит с пустым полями (заполнен только
 * MDM_ID - cluster id). Такие записи я называю "пустыми" и до сохранения в БД они не доходят. На момент написания этого комментария
 * таких записей было примерно 30% от общего количества.
 * <p>
 * Вышеописанное должно восприниматься системой, как записи, которые не имеют привязок. Допустим, есть привязка сотрудника
 * к cluster_id XXXX. Если придет запись cluster_id XXXX с пустым перечнем привязок сотрудника, то такую запись решено помечать,
 * как "is_deleted" (но не удалять), что позволит оставить функционал по отображению и подписанию закрытых магазинов нетронутым.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ClusterEmployeeServiceImpl implements ClusterEmployeeService {

    private final ClusterEmployeeRepository clusterEmployeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(ClusterEmployeeList clusterEmployeeList) {
        log.info("Saving cluster employee bindings...: {}", clusterEmployeeList);

        ClusterEmployeeListDto clusterEmployeeListDto = modelMapper.map(clusterEmployeeList, ClusterEmployeeListDto.class);

        if (!CollectionUtils.isEmpty(clusterEmployeeListDto.getClusterEmployeeBindingList())) {
            Predicate<ClusterEmployeeDto> notNullContent = employeeDto ->
                    employeeDto.getPersonalNumber() != null && !employeeDto.getPersonalNumber().isEmpty();

            List<ClusterEmployeeEntity> clusterEmployeeEntityList = getClusterEmployeeEntities(clusterEmployeeListDto, notNullContent);
            List<String> clusterIds = getClusterIds(clusterEmployeeListDto, notNullContent);

            if (clusterIds.size() > 0) { // пропускаем если список сотрудников кластера пустой
                log.info("Mark as \"is_deleted\" cluster employee ...: {} ", clusterIds);
                clusterEmployeeRepository.markAsDeletedByCfoId(clusterIds);
            }
            if (clusterEmployeeEntityList.size() > 0) {
                clusterEmployeeEntityList = clusterEmployeeRepository.saveAll(clusterEmployeeEntityList);
            }
            log.info("Saved cluster employee {} to DB", clusterEmployeeEntityList);
        }
    }

    private List<ClusterEmployeeEntity> getClusterEmployeeEntities(ClusterEmployeeListDto clusterEmployeeListDto,
                                                                   Predicate<ClusterEmployeeDto> notNullContent) {
        return clusterEmployeeListDto.getClusterEmployeeBindingList().stream()
                .filter(notNullContent)
                .map(employeeDto -> modelMapper.map(employeeDto, ClusterEmployeeEntity.class))
                .collect(Collectors.toList());
    }

    private List<String> getClusterIds(ClusterEmployeeListDto clusterEmployeeListDto,
                                       Predicate<ClusterEmployeeDto> notNullContent) {
        return clusterEmployeeListDto.getClusterEmployeeBindingList().stream()
                .map(ClusterEmployeeDto::getClusterId)
                .distinct()
                .collect(Collectors.toList());
    }
}
