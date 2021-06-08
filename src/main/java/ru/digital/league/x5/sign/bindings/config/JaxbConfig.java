package ru.digital.league.x5.sign.bindings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaxbConfig {

    @Bean("storeMarshaller")
    public Jaxb2Marshaller getStoreJaxbMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(StoreInfo.class);
        marshaller.setSchemas(
                new ClassPathResource("/xsd/schema/store/root.xsd"),
                new ClassPathResource("/xsd/schema/store/types.xsd"),
                new ClassPathResource("/xsd/schema/store/types_ns.xsd"));
        setMarshallerProperties(marshaller);
        return marshaller;
    }

    @Bean("employeeMarshaller")
    public Jaxb2Marshaller getEmployeeJaxbMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(EmployeeList.class);
        marshaller.setSchemas(
                new ClassPathResource("/xsd/schema/employee/root.xsd"),
                new ClassPathResource("/xsd/schema/employee/types.xsd"),
                new ClassPathResource("/xsd/schema/employee/types_ns.xsd"));
        setMarshallerProperties(marshaller);
        return marshaller;
    }

    @Bean("clusterEmployeeMarshaller")
    public Jaxb2Marshaller getClusterEmployeeJaxbMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(ClusterEmployeeList.class);
        marshaller.setSchemas(
                new ClassPathResource("/xsd/schema/cluster_employee/root.xsd"),
                new ClassPathResource("/xsd/schema/cluster_employee/types.xsd"),
                new ClassPathResource("/xsd/schema/cluster_employee/types_ns.xsd"));
        setMarshallerProperties(marshaller);
        return marshaller;
    }

    private void setMarshallerProperties(Jaxb2Marshaller marshaller) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setMarshallerProperties(properties);
    }
}
