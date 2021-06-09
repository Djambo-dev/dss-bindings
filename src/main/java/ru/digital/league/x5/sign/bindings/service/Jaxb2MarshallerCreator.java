package ru.digital.league.x5.sign.bindings.service;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public interface Jaxb2MarshallerCreator {
    Jaxb2Marshaller getStoreMarshaller();
    Jaxb2Marshaller getEmployeeMarshaller();
    Jaxb2Marshaller getClusterEmployeeMarshaller();

}
