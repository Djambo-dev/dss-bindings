package ru.digital.league.x5.sign.bindings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

@Configuration
public class ApplicationConfig {

    @Value("${log.integration-request.enable}")
    private boolean isEnable;

    @Bean
    public ServletContextRequestLoggingFilter requestLoggingFilter() {
        ServletContextRequestLoggingFilter loggingFilter = new ServletContextRequestLoggingFilter();
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(10000);
        loggingFilter.setAfterMessagePrefix("Request detail: ");
        return loggingFilter;
    }

    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
        registration.addUrlPatterns("/bind/*");
        registration.setEnabled(isEnable);
        return registration;
    }
}
