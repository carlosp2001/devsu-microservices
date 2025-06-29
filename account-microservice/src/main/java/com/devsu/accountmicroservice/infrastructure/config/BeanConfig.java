package com.devsu.accountmicroservice.infrastructure.config;

import com.devsu.library.infrastructure.mapper.PeticionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public PeticionMapper peticionMapper() {
        return new PeticionMapper();
    }
}
