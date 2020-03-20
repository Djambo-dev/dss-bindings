package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.repository.StoreRepository;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(StoreInfoDto storeInfo) {
        if (!CollectionUtils.isEmpty(storeInfo.getStores())) {
            log.info("Start process saving store({}) ", storeInfo.getStores().size());
            storeInfo.getStores().forEach(this::updateStore);
            log.info("End saving {} stores", storeInfo.getStores().size());
        }
    }

    /**
     * Алгоритм обновления/добавления магазина - когда приходит запись о магазине - мы обновляем эту запись по
     * коду магазина (mdm|sap id) и коду ЦФО (cfo id)
     */
    private void updateStore(StoreDto storeDto) {
        StoreEntity storeEntity = modelMapper.map(storeDto, StoreEntity.class);
        log.info("Process {}", storeEntity);
        storeRepository.save(storeEntity);
        log.info("Save stores {}", storeEntity);
    }

    @Override
    public List<StoreDto> getStoresByPersonalNumber(Long personalNumber) {
        List<StoreEntity> stores = storeRepository.findAllByPersonalNumber(personalNumber);

        List<StoreDto> storeDtos = stores.stream()
                .map(storeEntity -> modelMapper.map(storeEntity, StoreDto.class))
                .collect(Collectors.toList());

        log.info("Stores for document service: {}", storeDtos);

        return storeDtos;
    }

    @Override
    public StoreDto getStoreByStoreId(String storeId) {
        StoreEntity storeEntity = storeRepository.findByStoreKeyMdmStoreId(storeId);
        if (storeEntity == null) {
            log.warn("Not found store by id {}", storeId);
            return null;
        }
        StoreDto storeDto = modelMapper.map(storeEntity, StoreDto.class);
        log.debug("Found store {} by store id {}", storeDto, storeId);
        return storeDto;
    }
}
