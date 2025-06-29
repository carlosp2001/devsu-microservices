package com.devsu.accountmicroservice.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MovimientoIdPostgresGenerator implements com.devsu.accountmicroservice.application.port.out.MovimientoIdPostgresGenerator {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String generateId() {
        return jdbcTemplate.queryForObject("select generate_movimiento_id()", String.class);
    }
}
