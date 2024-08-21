package com.mx.mcsv.service_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http.authorizeExchange().anyExchange().authenticated()
                .and()
                .oauth2Login(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
    
    /*@Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistrations
                .fromIssuerLocation("http://localhost:8180/auth/realms/mcsv-app-shop")
                .clientId("spring-mcsv-new")
                .clientSecret("l1wjyO9kwzMalJjCgsKVN7C1cl92iAWM")
                .build();

        return new InMemoryReactiveClientRegistrationRepository(registration);
    }*/
}

