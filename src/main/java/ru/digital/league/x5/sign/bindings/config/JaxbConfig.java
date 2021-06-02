package ru.digital.league.x5.sign.bindings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.digital.league.x5.sign.bindings.xml.model.Store;
import ru.digital.league.x5.sign.bindings.xml.model.StoreInfo;

import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaxbConfig {

    @Bean("myMarshaller")
    public Jaxb2Marshaller createJaxbMarshaller() {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setSchemas(
                new ClassPathResource("/static/xsd/store/message_structure.xsd"),
                new ClassPathResource("/static/xsd/store/store_tags_desc.xsd"),
                new ClassPathResource("/static/xsd/store/store.xsd")
//                new ClassPathResource("/static/xsd/employee/message_structure.xsd"),
//                new ClassPathResource("/static/xsd/employee/employee.xsd"),
//                new ClassPathResource("/static/xsd/employee/employee_tags_desc.xsd"));
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.setMarshallerProperties(properties);
        marshaller.setClassesToBeBound(StoreInfo.class, Store.class);

        return marshaller;
    }

    @Bean
    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        Jaxb2Marshaller jaxbMarshaller = createJaxbMarshaller();
        marshallingHttpMessageConverter.setMarshaller(jaxbMarshaller);
        marshallingHttpMessageConverter.setUnmarshaller(jaxbMarshaller);
        return marshallingHttpMessageConverter;
    }
}
