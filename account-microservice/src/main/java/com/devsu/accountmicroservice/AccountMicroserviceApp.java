package com.devsu.accountmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.devsu.library.infrastructure.persistence.entity",
        "com.devsu.accountmicroservice.infrastructure.persistence.entity"
})
public class AccountMicroserviceApp {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroserviceApp.class, args);
    }

}
