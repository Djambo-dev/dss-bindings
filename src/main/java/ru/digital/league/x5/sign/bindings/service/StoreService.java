package ru.digital.league.x5.sign.bindings.service;

import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.List;

public interface StoreService {
    void save(StoreInfoDto storeInfo);

    List<Long> getStoreIdsByPersonalNumber(Long personalNumber);
}
