package ru.digital.league.x5.sign.bindings.service;

import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

public interface MarshallingService {
    StoreInfo getUnmarshalStores(String storeInfoXml);
    EmployeeList getUnmarshalEmployee(String employeeListXml);
    ClusterEmployeeList getUnmarshalClusterEmployee(String clusterEmployeeListXml);

}
