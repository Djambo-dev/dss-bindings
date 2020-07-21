package ru.digital.league.x5.sign.bindings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Выгрузка по привязкам сотрудников к ЦФО (Центру Финансовой Ответственности)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListDto {

    /**
     * Данные привязок сотрудников
     */
    private List<EmployeeDto> employeeBindingList;

}
