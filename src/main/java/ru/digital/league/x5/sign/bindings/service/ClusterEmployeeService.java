package ru.digital.league.x5.sign.bindings.service;

import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeListDto;

public interface ClusterEmployeeService {

    void save(ClusterEmployeeListDto clusterEmployeeListDto);

    ClusterEmployeeDto get(String personalNumber);

}
