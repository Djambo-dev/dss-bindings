package ru.digital.league.x5.sign.bindings.metric;

import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

@Component
@RequiredArgsConstructor
@Slf4j
public class CounterMetricsImpl implements CounterMetrics {

    private final Counter incomeStoreCounterMetrics;
    private final Counter incomeEmployeeCounterMetrics;
    private final Counter incomeClusterEmployeeCounterMetrics;

    @Override
    public void makeStoreBindingsMetrics(StoreInfo storeInfo) {
        try {
            incomeStoreCounterMetrics.increment(storeInfo.getStores().size());
        }catch (RuntimeException e){
            log.warn("Error during removing metrics [storeCounter]: {}", e.getMessage());
        }
    }

    @Override
    public void makeEmployeeMetrics(EmployeeList employeeList) {
        try {
            incomeEmployeeCounterMetrics.increment(employeeList.getEmployeeBindings().size());
        }catch (RuntimeException e){
            log.warn("Error during removing metrics [employeeCounter]: {}", e.getMessage());
        }
    }

    @Override
    public void makeClusterEmployeeMetrics(ClusterEmployeeList clusterEmployeeList) {
        try {
            incomeClusterEmployeeCounterMetrics.increment(clusterEmployeeList.getClusterEmployeeBindingList().size());
        }catch (RuntimeException e){
            log.warn("Error during removing metrics [clusterEmployeeCounter]: {}", e.getMessage());
        }
    }
}
