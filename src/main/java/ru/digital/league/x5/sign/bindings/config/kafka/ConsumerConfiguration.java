package ru.digital.league.x5.sign.bindings.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;
import ru.digital.league.x5.sign.bindings.dto.bad.BadEmployeeBindingInfoDto;
import ru.digital.league.x5.sign.bindings.dto.bad.BadStoreInfoDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.reconnect.backoff.ms}")
    private Integer backoff;
    @Value("${spring.kafka.reconnect.backoff.max_ms}")
    private Integer backoffMax;
    @Value("${kafka.ssl.enable}")
    private Boolean sslEnable;
    @Value("${kafka.ssl.key-store-path}")
    private String keyStorePath;
    @Value("${kafka.ssl.key-store-password}")
    private String keyStorePassword;
    @Value("${kafka.ssl.trust-store-path}")
    private String trustStorePath;
    @Value("${kafka.ssl.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public ConsumerFactory<String, StoreInfoDto> storeKafkaConsumerFactory() {
        return declareConsumerFactory(StoreInfoDto.class, bootstrapServers, groupId, new BadStoreInfoDto());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StoreInfoDto> storeKafkaListenerContainerFactory() {
        return declareListenerContainerFactory(storeKafkaConsumerFactory());
    }

    @Bean
    public ConsumerFactory<String, EmployeeBindingInfoDto> employeeBindingKafkaConsumerFactory() {
        return declareConsumerFactory(EmployeeBindingInfoDto.class, bootstrapServers, groupId, new BadEmployeeBindingInfoDto());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmployeeBindingInfoDto> employeeBindingKafkaListenerContainerFactory() {
        return declareListenerContainerFactory(employeeBindingKafkaConsumerFactory());
    }

    private <T> ConcurrentKafkaListenerContainerFactory<String, T> declareListenerContainerFactory(ConsumerFactory<String, T> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckOnError(false);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(-1));
        factory.setStatefulRetry(true);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new AlwaysRetryPolicy());
        retryTemplate.setBackOffPolicy(new ExponentialBackOffPolicy());
        factory.setRetryTemplate(retryTemplate);
        return factory;
    }

    private <T> ConsumerFactory<String, T> declareConsumerFactory(Class<T> type, String bootstrapServers, String groupId, T badDtoInstance) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer2.class);
        props.put(ErrorHandlingDeserializer2.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, backoff);
        props.put(ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, backoffMax);
        if (sslEnable) {
            props.put("security.protocol", "SSL");
            props.put("ssl.keystore.location", keyStorePath);
            props.put("ssl.keystore.password", keyStorePassword);
            props.put("ssl.truststore.location", trustStorePath);
            props.put("ssl.truststore.password", trustStorePassword);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        final JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<>(type, mapper, false);
        jsonDeserializer.addTrustedPackages("*");
        ErrorHandlingDeserializer2<T> errorHandlingDeserializer = new ErrorHandlingDeserializer2<>(jsonDeserializer);
        errorHandlingDeserializer.setFailedDeserializationFunction(DeserializeErrorHelper.handleFunction(badDtoInstance));
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), errorHandlingDeserializer);
    }
}
