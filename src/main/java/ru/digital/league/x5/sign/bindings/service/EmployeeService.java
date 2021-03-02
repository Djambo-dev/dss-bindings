package ru.digital.league.x5.sign.bindings.service;

import ru.digital.league.x5.sign.bindings.dto.EmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeListDto;

public interface EmployeeService {
    void save(EmployeeListDto employeeBindingInfo);

    EmployeeDto get(String personalNumber);
}
