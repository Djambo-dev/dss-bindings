package ru.digital.league.x5.sign.bindings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Данные сотрудника, привязанного к ЦФО (Центру Финансовой Ответственности)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBindingDto {

    /**
     * Код ЦФО
     */
    private String cfoId;

    /**
     * Табельный номер сотрудника
     */
    private Long personalNumber;

    /**
     * Логин сотрудника в AD (без указания домена)
     */
    private String personalLogin;

    /**
     * Название должности
     */
    private String positionName;

    /**
     * Код должности
     */
    private Long positionId;

}
