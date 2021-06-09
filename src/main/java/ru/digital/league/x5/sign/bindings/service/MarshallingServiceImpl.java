package ru.digital.league.x5.sign.bindings.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.exception.JaxbValidationException;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Slf4j
@AllArgsConstructor
@Service
public class MarshallingServiceImpl implements MarshallingService {

    private Jaxb2MarshallerCreator marshallerCreator;

    @Override
    public StoreInfo getUnmarshalStores(String storeInfoXml) {
        StoreInfo storeInfo;
        try {
            Jaxb2Marshaller marshaller = marshallerCreator.getStoreMarshaller();
            storeInfo = (StoreInfo) marshaller
                    .unmarshal(getSource(storeInfoXml));
        } catch (Exception e) {
            throw new JaxbValidationException(storeInfoXml, e);
        }
        return storeInfo;
    }

    @Override
    public EmployeeList getUnmarshalEmployee(String employeeListXml) {
        EmployeeList employeeList;
        try {
            employeeList = (EmployeeList) marshallerCreator.getEmployeeMarshaller()
                    .unmarshal(getSource(employeeListXml));
        } catch (Exception e) {
            throw new JaxbValidationException(employeeListXml, e);
        }
        return employeeList;
    }

    @Override
    public ClusterEmployeeList getUnmarshalClusterEmployee(String clusterEmployeeListXml) {
        ClusterEmployeeList clusterEmployeeList;
        try {
            clusterEmployeeList = (ClusterEmployeeList) marshallerCreator.getClusterEmployeeMarshaller()
                    .unmarshal(getSource(clusterEmployeeListXml));
        } catch (Exception e) {
            throw new JaxbValidationException(clusterEmployeeListXml, e);
        }
        return clusterEmployeeList;
    }

    private StreamSource getSource(String xmlString) {
        return new StreamSource(new StringReader(xmlString));
    }
}

