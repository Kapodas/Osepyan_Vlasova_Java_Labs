package com.electronicsstore.lab4jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Lab4JmsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Lab4JmsApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Lab4JmsApplication.class);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
