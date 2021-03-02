package ru.digital.league.x5.sign.bindings.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableKafka
@EnableAsync
@ConfigurationProperties(prefix = "user")
public class ApplicationConfig {
    //TODO web client, ssl etc.

    private List<String> positionId = new ArrayList<>();

    @Bean
    public List<String> getPositionId(){
        return this.positionId;
    }
}
