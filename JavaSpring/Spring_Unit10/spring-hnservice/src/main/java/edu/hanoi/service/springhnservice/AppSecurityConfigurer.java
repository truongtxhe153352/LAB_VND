package edu.hanoi.service.springhnservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("nguoidung").password("{noop}123456").roles("USER")
                .and()
                .withUser("quantri").password("{noop}654321")
                .roles("ADMIN", "USER").and().and().authenticationProvider(customAuthenticationProvider);

        //auth.authenticationProvider(custoAuthenticationProvider());
    }

    @Autowired
//    public AuthenticationProvider customAuthenticationProvider() {
//        AuthenticationProvider provider = new HnUserAuthProvider();
//        return provider;
//    }
    public AuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/").
                hasRole("USER").
                anyRequest().
                authenticated().
                and().
                httpBasic();
    }
}
