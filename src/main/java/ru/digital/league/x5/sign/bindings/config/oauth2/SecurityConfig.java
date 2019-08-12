package ru.digital.league.x5.sign.bindings.config.oauth2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@ConditionalOnProperty(matchIfMissing = false, name = "security-enabled")
@Configuration
@EnableResourceServer
public class SecurityConfig {
}