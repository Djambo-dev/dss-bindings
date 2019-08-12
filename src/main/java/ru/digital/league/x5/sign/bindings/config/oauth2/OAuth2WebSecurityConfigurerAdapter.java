package ru.digital.league.x5.sign.bindings.config.oauth2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnMissingBean(SecurityConfig.class)
public class OAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private List<String> ignoredURLS = new ArrayList<>();

    @Override
    public void configure(WebSecurity web) {
        ignoredURLS.add("/**");
        web.ignoring().antMatchers(ignoredURLS.toArray(new String[0]));
    }
}
