package ru.digital.league.x5.sign.bindings.service;

import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.List;

public interface StoreService {
    /**
     * Сохраняем/обновляем список магазинов в БД
     */
    void save(StoreInfoDto storeInfo);

    /**
     * Ищем привязанные к сотруднику магазины по его табельному номеру
     * @param personalNumber табельный номер
     */
    List<StoreDto> getStoresByPersonalNumber(String personalNumber);

    /**
     * Ищем магазин по его mdm|sap идентификатору
     * @param storeId mdm|sap id
     */
    StoreDto getStoreByStoreId(String storeId);
}
