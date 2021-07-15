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
public class ClusterEmployeeServiceImpl implements ClusterEmployeeService {

    private final static String UPDATED_CLUSTER_IDS = "updatedClusterIds";
    private final static String EMPTY_CLUSTER_IDS = "emptyClusterIds";

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

            HashMap <String, List<String>> clusterIds = getClusterIds(clusterEmployeeListDto, notNullContent);

            if(clusterIds.get(EMPTY_CLUSTER_IDS).size() > 0) {
                log.info("Mark as \"is_deleted\" cluster employee ...: {} ", clusterIds.get(EMPTY_CLUSTER_IDS));
                clusterEmployeeRepository.markAsDeletedByCfoId(clusterIds.get(EMPTY_CLUSTER_IDS));
            }

            if (clusterEmployeeEntityList.size() > 0) { // пропускаем если список сотрудников кластера пустой
                clusterEmployeeRepository.deleteAllByClusterIdIn(clusterIds.get(UPDATED_CLUSTER_IDS));
                log.info("Deleted existing cluster employee ...: {}", clusterIds.get(UPDATED_CLUSTER_IDS));
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

    private HashMap<String, List<String>> getClusterIds(ClusterEmployeeListDto clusterEmployeeListDto,
                                                        Predicate<ClusterEmployeeDto> notNullContent) {
        List<String> updatedClusterIds = new ArrayList<>();
        List<String> emptyClusterIds   = new ArrayList<>();
        HashMap<String, List<String>> cfoIds = new HashMap<>();

        clusterEmployeeListDto.getClusterEmployeeBindingList().stream()
                .filter(clusterEmployeeDto -> {
                    if (notNullContent.test(clusterEmployeeDto)) {
                        return true;
                    }else{
                        emptyClusterIds.add(clusterEmployeeDto.getClusterId());
                        return false;
                    }
                })
                .map(ClusterEmployeeDto::getClusterId)
                .distinct()
                .forEach(cfoId -> updatedClusterIds.add(cfoId));
        cfoIds.put(UPDATED_CLUSTER_IDS, updatedClusterIds);
        cfoIds.put(EMPTY_CLUSTER_IDS, emptyClusterIds);
        return cfoIds;
    }
}
