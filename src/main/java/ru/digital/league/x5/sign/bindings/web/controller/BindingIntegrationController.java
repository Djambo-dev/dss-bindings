package ru.digital.league.x5.sign.bindings.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.digital.league.x5.sign.bindings.metric.CounterMetrics;
import ru.digital.league.x5.sign.bindings.service.ClusterEmployeeService;
import ru.digital.league.x5.sign.bindings.service.EmployeeService;
import ru.digital.league.x5.sign.bindings.service.StoreService;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bind")
@Slf4j
public class BindingIntegrationController {

    private final StoreService storeService;
    private final EmployeeService employeeBindingService;
    private final ClusterEmployeeService clusterEmployeeService;
    private final CounterMetrics counterMetrics;

    /**
     * Загрузка информации по магазинам
     */
    @PostMapping(value = "/store", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loadStore(@RequestBody StoreInfo storeInfo) {
        log.info("Got store from IBM MDM");
        counterMetrics.makeStoreBindingsMetrics(storeInfo);
        log.debug("Store info: {}", storeInfo);
        storeService.save(storeInfo);
    }

    /**
     * Загрузка информации по привязкам сотрудников к ЦФО
     */
    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loadEmployeeBindings(@RequestBody EmployeeList employeeList) {
        log.info("Got employee binding from IBM MDM");
        counterMetrics.makeEmployeeMetrics(employeeList);
        log.debug("Employee binding info: {}", employeeList);
        employeeBindingService.save(employeeList);
    }

    /**
     * Загрузка информации по привязкам сотрудников к кластерам
     */
    @PostMapping(value = "/cluster/employee", consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loadClusterEmployeeBindings(@RequestBody ClusterEmployeeList employeeList) {
        log.info("Got cluster employee binding from IBM MDM");
        counterMetrics.makeClusterEmployeeMetrics(employeeList);
        log.debug("Cluster employee binding info: {}", employeeList);
        clusterEmployeeService.save(employeeList);
    }
}
