package ru.digital.league.x5.sign.bindings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaxbConfig {

    private static final String STORE_XML_MODEL = "store";
    private static final String EMPLOYEE_XML_MODEL = "employee";
    private static final String CLUSTER_EMPLOYEE_XML_MODEL = "cluster_employee";

    private static final String COMMON_PATH = "/xsd/schema/";
    private static final String ROOT_XSD = "/root.xsd";
    private static final String TYPES_XSD = "/types.xsd";
    private static final String TYPES_NS_XSD = "/types_ns.xsd";

    @Bean("storeMarshaller")
    @Scope("prototype")
    public Jaxb2Marshaller getStoreJaxbMarshaller() {
        Jaxb2Marshaller marshaller = getMarshaller(STORE_XML_MODEL);
        marshaller.setClassesToBeBound(StoreInfo.class);
        return marshaller;
    }

    @Bean("employeeMarshaller")
    public Jaxb2Marshaller getEmployeeJaxbMarshaller() {
        Jaxb2Marshaller marshaller = getMarshaller(EMPLOYEE_XML_MODEL);
        marshaller.setClassesToBeBound(EmployeeList.class);
        return marshaller;
    }

    @Bean("clusterEmployeeMarshaller")
    public Jaxb2Marshaller getClusterEmployeeJaxbMarshaller() {
        Jaxb2Marshaller marshaller = getMarshaller(CLUSTER_EMPLOYEE_XML_MODEL);
        marshaller.setClassesToBeBound(ClusterEmployeeList.class);
        return marshaller;
    }

    private Jaxb2Marshaller getMarshaller(String xmlModel) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setSchemas(getSchemas(xmlModel));
        Map<String, Object> properties = new HashMap<>();
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setMarshallerProperties(properties);
        return marshaller;
    }

    private Resource[] getSchemas(String xmlModel) {
        return new Resource[]{
                new ClassPathResource(COMMON_PATH + xmlModel + ROOT_XSD),
                new ClassPathResource(COMMON_PATH + xmlModel + TYPES_XSD),
                new ClassPathResource(COMMON_PATH + xmlModel + TYPES_NS_XSD)};
    }
}
