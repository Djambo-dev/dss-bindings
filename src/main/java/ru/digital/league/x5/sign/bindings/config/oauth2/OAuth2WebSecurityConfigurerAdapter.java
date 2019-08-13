package ru.digital.league.x5.sign.bindings.config.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${security-enabled}")
    private boolean securityEnabled;

    private List<String> ignoredURLS = new ArrayList<>();

    @Override
    public void configure(WebSecurity web) {
        if (!securityEnabled) {
            ignoredURLS.add("/**");
        }
        ignoredURLS.add("/actuator/**");
        web.ignoring().antMatchers(ignoredURLS.toArray(new String[0]));
    }
}
