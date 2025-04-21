package com.microservice.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UserServiceConfig {

    @Bean
    public ObjectMapper objectMapperInti(){
        return new ObjectMapper();
    }
}
