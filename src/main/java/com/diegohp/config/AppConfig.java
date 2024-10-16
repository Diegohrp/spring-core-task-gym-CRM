package com.diegohp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.diegohp.*")
@PropertySource("classpath:application.properties")
public class AppConfig {


}
