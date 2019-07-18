package ru.digital.league.x5.sign.bindings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Выгрузка по магазинам
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoDto {

    /**
     * Данные магазинов
     */
    private List<StoreDto> stores;

}
