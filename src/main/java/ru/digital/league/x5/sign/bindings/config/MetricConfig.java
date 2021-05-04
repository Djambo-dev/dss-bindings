package ru.digital.league.x5.sign.bindings.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.digital.league.x5.sign.bindings.web.controller.BindingIntegrationController;

@Configuration
public class MetricConfig {

    @Autowired
    MeterRegistry meterRegistry;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .meterFilter(MeterFilter.denyNameStartsWith("jvm"))
                .meterFilter(MeterFilter.denyNameStartsWith("logback"))
                .commonTags("microservice", "dss-bindings");
    }

    /**
     *  Метирики для подсчета количества входящих обьектов (магазины, сотрудники, кластерные сотрудники)
     */
    @Bean("incomeStoreCounterMetrics")
    public Counter getCountOfStore(){
        return meterRegistry.counter("count.of.income.store",
                "object", "Store",
                "region", "controller",
                "name", BindingIntegrationController.class.getSimpleName());

    }

    @Bean("incomeEmployeeCounterMetrics")
    public Counter getCountOfEmployee(){
        return meterRegistry.counter("count.of.income.employee",
                "object", "EmployeeBinding",
                "region", "controller",
                "name", BindingIntegrationController.class.getSimpleName());

    }

    @Bean("incomeClusterEmployeeCounterMetrics")
    public Counter getCountOfClusterEmployee(){
        return meterRegistry.counter("count.of.income.cluster.employee",
                "object", "ClusterEmployeeBinding",
                "region", "controller",
                "name", BindingIntegrationController.class.getSimpleName());

    }
}
