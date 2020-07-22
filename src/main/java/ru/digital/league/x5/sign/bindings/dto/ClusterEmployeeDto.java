package ru.digital.league.x5.sign.bindings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Данные сотрудника, привязанного к Кластеру
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClusterEmployeeDto {

    /**
     * Идентификатор кластера
     */
    private String clusterId;

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
     * ФИО сотрудника
     */
    private String fullName;

    /**
     * Название роли
     */
    private String role;

    /**
     * Название роли
     */
    private String email;

    /**
     * Если это поле инициализировано, то оно содержит основной табельный номер,
     * а personalNumber - это табельный номер совместителя
     */
    private String linkedPersonalNumber;

}
