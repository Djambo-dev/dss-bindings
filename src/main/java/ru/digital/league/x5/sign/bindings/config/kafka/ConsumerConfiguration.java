package ru.digital.league.x5.sign.bindings.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import ru.digital.league.x5.sign.bindings.dto.EmployeeBindingInfoDto;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfiguration {

    private final ObjectMapper kafkaMapper;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
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
    public ConcurrentKafkaListenerContainerFactory<String, StoreInfoDto> storeKafkaListenerContainerFactory() {

        var jsonDeserializer = new JsonDeserializer<>(StoreInfoDto.class, kafkaMapper, false);
        var consumerFactory = new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), jsonDeserializer);

        var factory = new ConcurrentKafkaListenerContainerFactory<String, StoreInfoDto>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckOnError(false);
        factory.setStatefulRetry(true);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new AlwaysRetryPolicy());
        retryTemplate.setBackOffPolicy(new ExponentialBackOffPolicy());
        factory.setRetryTemplate(new RetryTemplate());

        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmployeeBindingInfoDto> employeeBindingKafkaListenerContainerFactory() {

        var jsonDeserializer = new JsonDeserializer<>(EmployeeBindingInfoDto.class, kafkaMapper, false);
        var consumerFactory = new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), jsonDeserializer);

        var factory = new ConcurrentKafkaListenerContainerFactory<String, EmployeeBindingInfoDto>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckOnError(false);
        factory.setStatefulRetry(true);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new AlwaysRetryPolicy());
        retryTemplate.setBackOffPolicy(new ExponentialBackOffPolicy());
        factory.setRetryTemplate(new RetryTemplate());

        return factory;
    }

    private Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        if (sslEnable) {
            props.put("security.protocol", "SSL");
            props.put("ssl.keystore.location", keyStorePath );
            props.put("ssl.keystore.password", keyStorePassword);
            props.put("ssl.truststore.location", trustStorePath);
            props.put("ssl.truststore.password", trustStorePassword);
        }
        return props;
    }
}
