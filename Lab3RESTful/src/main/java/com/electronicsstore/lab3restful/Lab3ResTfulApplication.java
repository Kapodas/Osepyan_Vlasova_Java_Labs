package com.electronicsstore.lab3restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Lab3ResTfulApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Lab3ResTfulApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Lab3ResTfulApplication.class);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
