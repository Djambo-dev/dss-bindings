package ru.digital.league.x5.sign.bindings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Данные магазина
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    /**
     * Код завода SAP
     */
    private Long mdmStoreId;

    /**
     * Название магазина для отображения
     */
    private String name;

    /**
     * Код ЦФО
     */
    private String cfoId;

    /**
     * Адрес магазина
     */
    private String address;

    /**
     * Дата открытия магазина
     */
    private LocalDate openDate;

    /**
     * Дата закрытия магазина
     */
    private LocalDate closeDate;

}