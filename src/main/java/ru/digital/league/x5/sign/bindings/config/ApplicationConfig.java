package ru.digital.league.x5.sign.bindings.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableKafka
@EnableAsync
public class ApplicationConfig {
    //TODO web client, ssl etc.
}
