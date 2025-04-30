package com.expense.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.expense.service.repository")
public class ServiceApplication {
    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(ServiceApplication.class, args);
    }
}
