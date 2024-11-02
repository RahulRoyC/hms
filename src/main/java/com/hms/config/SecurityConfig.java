package com.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity//to take incoming http request
    ) throws Exception {
        //h(cd)2
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        //haap
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

//        httpSecurity.authorizeHttpRequests().
//                requestMatchers("/api/v1/users/login","/api/v1/users/signup",
//                        "/api/v1/users/signup-property-owner").// ye vale url open rahenge bass
//                permitAll().
//                requestMatchers("/api/v1/country/addCountry").
//                hasAnyRole("ADMIN","OWNER").
//                anyRequest().
//                authenticated();//other than that forbidden ho jayenge

        return httpSecurity.build();//this build the above code in it
    }
}