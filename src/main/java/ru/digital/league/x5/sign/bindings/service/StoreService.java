package ru.digital.league.x5.sign.bindings.service;

import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.List;

public interface StoreService {
    /**
     * Сохраняем/обновляем список магазинов в БД
     */
    void save(StoreInfoDto storeInfo);

    List<StoreDto> getStoresByPersonalNumber(Long personalNumber);

    StoreDto getStoreByStoreId(String storeId);
}
