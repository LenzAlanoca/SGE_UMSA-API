package com.sgeumsaapi.sge_umsa_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class SgeUmsaApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SgeUmsaApiApplication.class, args);
    }
}