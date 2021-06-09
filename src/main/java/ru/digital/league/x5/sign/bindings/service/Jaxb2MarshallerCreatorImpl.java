package ru.digital.league.x5.sign.bindings.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Jaxb2MarshallerCreatorImpl implements Jaxb2MarshallerCreator {

    private final static String STORE_MARSHALLER = "storeMarshaller";
    private final static String EMPLOYEE_MARSHALLER = "employeeMarshaller";
    private final static String CLUSTER_EMPLOYEE_MARSHALLER = "clusterEmployeeMarshaller";

    ApplicationContext context;

    @Override
    public Jaxb2Marshaller getStoreMarshaller() {
        return context.getBean(STORE_MARSHALLER, Jaxb2Marshaller.class);
    }

    @Override
    public Jaxb2Marshaller getEmployeeMarshaller() {
        return context.getBean(EMPLOYEE_MARSHALLER, Jaxb2Marshaller.class);
    }

    @Override
    public Jaxb2Marshaller getClusterEmployeeMarshaller() {
        return context.getBean(CLUSTER_EMPLOYEE_MARSHALLER, Jaxb2Marshaller.class);
    }
}
