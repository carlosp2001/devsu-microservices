package com.devsu.usermicroservice.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClienteIdPostgresGenerator implements com.devsu.usermicroservice.application.port.out.ClienteIdPostgresGenerator {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String generateId() {
        return jdbcTemplate.queryForObject("select generate_cliente_id()", String.class);
    }
}
