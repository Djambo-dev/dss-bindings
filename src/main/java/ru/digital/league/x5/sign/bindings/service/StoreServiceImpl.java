package ru.digital.league.x5.sign.bindings.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.repository.StoreRepository;
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
    public void save(StoreInfoDto storeInfo) {

        if (!CollectionUtils.isEmpty(storeInfo.getStores())) {
            List<StoreEntity> storeEntities = storeInfo.getStores().stream()
                    .map(storeDto -> modelMapper.map(storeDto, StoreEntity.class))
                    .collect(Collectors.toList());

            logger.info("Saving stores {} to DB", storeEntities);

            storeRepository.saveAll(storeEntities);
        }
    }

    @Override
    public List<Long> getStoreIdsByPersonalNumber(Long personalNumber) {
        List<Long> storeIds = storeRepository.findStoreIdsByPersonalNumber(personalNumber);

        if (!CollectionUtils.isEmpty(storeIds)) {
            String ids = storeIds.stream()
                    .map(id -> id.toString())
                    .collect(Collectors.joining(", "));
            logger.info("Store ids for document service: [{}]", ids);
        }

        return storeIds;
    }
}
