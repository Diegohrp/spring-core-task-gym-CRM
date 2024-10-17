package com.diegohp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages = "com.diegohp.*")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("App Logger");
    }
}
