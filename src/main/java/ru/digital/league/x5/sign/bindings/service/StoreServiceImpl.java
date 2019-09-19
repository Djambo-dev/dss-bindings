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
            log.info("Saving store records...:{}", storeInfo);
            List<StoreEntity> storeEntities = storeInfo.getStores().stream()
                    .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                    .collect(Collectors.toList());
            List<String> cfoIds = storeEntities.stream()
                    .map(StoreEntity::getCfoId)
                    .distinct()
                    .collect(Collectors.toList());
            storeRepository.deleteAllByCfoIdIn(cfoIds);
            log.info("Deleted existing store records...: {}", cfoIds);
            storeEntities = storeRepository.saveAll(storeEntities);
            log.info("Saved stores {} to DB", storeEntities);
        }
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
        StoreEntity storeEntity = storeRepository.findByMdmStoreId(storeId);
        if (storeEntity == null){
            log.warn("Not found store by id {}", storeId);
            return null;
        }
        StoreDto storeDto = modelMapper.map(storeEntity, StoreDto.class);
        log.debug("Found store {} by store id {}", storeDto, storeId);
        return storeDto;
    }
}
