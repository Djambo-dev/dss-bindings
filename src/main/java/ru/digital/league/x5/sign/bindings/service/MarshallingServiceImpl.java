package ru.digital.league.x5.sign.bindings.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import ru.digital.league.x5.sign.bindings.exception.JaxbValidationException;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Service
@Slf4j
public class MarshallingServiceImpl implements MarshallingService {

    private Jaxb2Marshaller storeMarshaller;
    private Jaxb2Marshaller employeeMarshaller;
    private Jaxb2Marshaller clusterEmployeeMarshaller;

    public MarshallingServiceImpl(
            Jaxb2Marshaller storeMarshaller,
            Jaxb2Marshaller employeeMarshaller,
            Jaxb2Marshaller clusterEmployeeMarshaller) {
        this.storeMarshaller = storeMarshaller;
        this.employeeMarshaller = employeeMarshaller;
        this.clusterEmployeeMarshaller = clusterEmployeeMarshaller;
    }

    @Override
    public StoreInfo getUnmarshalStores(String storeInfoXml) {
        StoreInfo storeInfo;
        try {
            storeInfo = (StoreInfo) storeMarshaller.unmarshal(getSource(storeInfoXml));
        } catch (Exception e) {
            throw new JaxbValidationException(storeInfoXml, e);
        }
        return storeInfo;
    }

    @Override
    public EmployeeList getUnmarshalEmployee(String employeeListXml) {
        EmployeeList employeeList;
        try {
            employeeList = (EmployeeList) employeeMarshaller.unmarshal(getSource(employeeListXml));
        } catch (Exception e) {
            throw new JaxbValidationException(employeeListXml, e);
        }
        return employeeList;
    }

    @Override
    public ClusterEmployeeList getUnmarshalClusterEmployee(String clusterEmployeeListXml) {
        ClusterEmployeeList clusterEmployeeList;
        try {
            clusterEmployeeList = (ClusterEmployeeList) clusterEmployeeMarshaller.unmarshal(getSource(clusterEmployeeListXml));
        } catch (Exception e) {
            throw new JaxbValidationException(clusterEmployeeListXml, e);
        }
        return clusterEmployeeList;
    }

    private StreamSource getSource(String xmlString) {
        return new StreamSource(new StringReader(xmlString));
    }
}
