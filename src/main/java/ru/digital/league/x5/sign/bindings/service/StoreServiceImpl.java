package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.repository.StoreRepository;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.xml.model.Store;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    @Value("${user.position-id}")
    private final List<Long> positionIdList;

    // присвоение значения переменной требуется для прохождения тестов (в противном случае intervalDays = null), а также,
    // чтобы не создавать конструктор данного класса с присвоением final переменной значения из application.yml
    @Value("${interval.days}")
    private Integer intervalDays = 30;

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    /**
     * Алгоритм обновления/добавления магазина - когда приходит запись о магазине - мы обновляем эту запись по
     * коду магазина (mdm|sap id) и коду ЦФО (cfo id)
     */
    @Override
    @Transactional
    public void save(StoreInfo storeInfo) {
        List<Store> storeList = storeInfo.getStores();
        if (!CollectionUtils.isEmpty(storeList)) {
            log.info("Start process saving store({}) ", storeList.size());
            List<StoreEntity> storeEntityList = storeList.stream()
                    .map(s -> modelMapper.map(s, StoreEntity.class))
                    .collect(Collectors.toList());
            log.info("Processing {}", storeEntityList);
            storeRepository.saveAll(storeEntityList);
            log.info("End saving {} stores", storeInfo.getStores().size());
        }
    }

    @Override
    public List<StoreDto> getStoresByPersonalNumber(String personalNumber) {
        long begin = System.currentTimeMillis();
        List<StoreEntity> storeList = storeRepository.findAllByPersonalNumber(personalNumber);
        log.info("Binding by employee table ={}", System.currentTimeMillis() - begin);

        begin = System.currentTimeMillis();
        List<StoreEntity> storeListForCluster = storeRepository.findAllByClusterPersonalNumber(personalNumber);
        log.info("Binding by cluster employee table ={}", System.currentTimeMillis() - begin);

        List<StoreEntity> unionStoreEntityList = new LinkedList<>();
        unionStoreEntityList.addAll(storeList);
        unionStoreEntityList.addAll(storeListForCluster.stream().filter(se -> !storeList.contains(se)).collect(Collectors.toList()));

        if (!positionIdList.isEmpty()) {
            begin = System.currentTimeMillis();
            List<StoreEntity> closedStoreListForAllEmployee = storeRepository.findAllClosedShopByPersonalNumber(personalNumber, intervalDays, positionIdList);
            log.info("Binding closed shop by employee and cluster employee table ={}", System.currentTimeMillis() - begin);
            unionStoreEntityList.addAll(closedStoreListForAllEmployee);
        }

        List<StoreDto> storeDtoList = unionStoreEntityList.stream()
                .map(storeEntity -> modelMapper.map(storeEntity, StoreDto.class))
                .collect(Collectors.toList());

        log.info("Stores for personalNumber={}: {}", personalNumber, storeDtoList);

        return storeDtoList;
    }

    @Override
    public StoreDto getStoreByStoreId(String storeId) {
        StoreEntity storeEntity = storeRepository.findByMdmStoreId(storeId);
        if (storeEntity == null) {
            log.warn("Not found store by id {}", storeId);
            return null;
        }
        StoreDto storeDto = modelMapper.map(storeEntity, StoreDto.class);
        log.debug("Found store {} by store id {}", storeDto, storeId);
        return storeDto;
    }
}
