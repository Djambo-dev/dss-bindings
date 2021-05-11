package ru.digital.league.x5.sign.bindings.metric;

import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

public interface CounterMetrics {
    void makeStoreBindingsMetrics(StoreInfo storeInfo);
    void makeEmployeeMetrics(EmployeeList employeeList);
    void makeClusterEmployeeMetrics(ClusterEmployeeList clusterEmployeeList);

}
