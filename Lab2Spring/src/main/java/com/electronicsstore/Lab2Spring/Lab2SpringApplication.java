package com.electronicsstore.Lab2Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Lab2SpringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Lab2SpringApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Lab2SpringApplication.class);
	}
}
