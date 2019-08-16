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
            List<StoreEntity> storeEntities = storeInfo.getStores().stream()
                    .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                    .collect(Collectors.toList());

            List<String> cfoIds = storeEntities.stream()
                    .map(StoreEntity::getCfoId)
                    .distinct()
                    .collect(Collectors.toList());

            log.info("Deleting existing store records");

            storeRepository.deleteAllByCfoIdIn(cfoIds);

            log.info("Saving stores {} to DB", storeEntities);

            storeRepository.saveAll(storeEntities);
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
}
