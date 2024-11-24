package com.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //h(cd)2
        http.csrf().disable().cors().disable();
        //csrf disable reason -
        //cors disable reason

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        //haap
        http.authorizeHttpRequests().anyRequest().permitAll(); //If we want that all url can be accessed


//        http.authorizeHttpRequests().requestMatchers("/api/v1/users/signupuser",
//                        "/api/v1/users/login","/api/v1/users/signupowner")
//                .permitAll().requestMatchers("/api/v1/country/addcountry").hasAnyRole("ADMIN","OWNER")
//                .anyRequest().authenticated();//will filter those urls which you want to be accessed without token

        /*DefaultSecurityFilterChain build = http.build();
        return build;*/

        return http.build();
    }
}
