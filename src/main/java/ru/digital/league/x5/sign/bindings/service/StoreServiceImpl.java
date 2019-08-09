package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class StoreServiceImpl implements StoreService {

    private final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(StoreInfoDto storeInfo) {

        if (!CollectionUtils.isEmpty(storeInfo.getStores())) {
            List<StoreEntity> storeEntities = storeInfo.getStores().stream()
                    .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                    .collect(Collectors.toList());

            List<String> mdmStoreIds = storeEntities.stream()
                    .map(StoreEntity::getMdmStoreId)
                    .collect(Collectors.toList());

            logger.info("Deleting existing store records");

            storeRepository.deleteAllByMdmStoreIdIn(mdmStoreIds);

            logger.info("Saving stores {} to DB", storeEntities);

            storeRepository.saveAll(storeEntities);
        }
    }

    @Override
    public List<StoreDto> getStoresByPersonalNumber(Long personalNumber) {
        List<StoreEntity> stores = storeRepository.findAllByPersonalNumber(personalNumber);

        List<StoreDto> storeDtos = stores.stream()
                .map(storeEntity -> modelMapper.map(storeEntity, StoreDto.class))
                .collect(Collectors.toList());

        logger.info("Stores for document service: {}", storeDtos);

        return storeDtos;
    }
}
