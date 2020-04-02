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
public class EmployeeDto {

    /**
     * Код ЦФО
     */
    private String cfoId;

    /**
     * Табельный номер сотрудника
     */
    private String personalNumber;

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

    /**
     * Если это поле инициализировано, то оно содержит основной табельный номер,
     * а personalNumber - это табельный номер совместителя
     */
    private String linkedPersonalNumber;

}
