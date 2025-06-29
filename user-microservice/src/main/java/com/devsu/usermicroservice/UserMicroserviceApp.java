package com.devsu.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.devsu.usermicroservice.infrastructure.persistence.entity",
        "com.devsu.library.infrastructure.persistence.entity"
})
public class UserMicroserviceApp {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApp.class, args);
    }

}
