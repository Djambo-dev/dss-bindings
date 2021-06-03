package ru.digital.league.x5.sign.bindings.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.digital.league.x5.sign.bindings.xml.model.XmlObject;

import javax.xml.bind.Marshaller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaxbConfig {

    @Autowired
    private XmlObject[] xmlObjects;

    @Bean
    public Jaxb2Marshaller createJaxbMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setSchemas(
                new ClassPathResource("/xsd/schema/root.xsd"),
                new ClassPathResource("/xsd/schema/types.xsd"),
                new ClassPathResource("/xsd/schema/types_ns.xsd"));
        marshaller.setClassesToBeBound(Arrays.stream(xmlObjects).map(XmlObject::getClass).toArray(Class[]::new));

        Map<String, Object> properties = new HashMap<>();
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.setMarshallerProperties(properties);
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
