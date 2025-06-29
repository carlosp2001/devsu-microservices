package com.devsu.usermicroservice.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonaIdPostgresGenerator implements com.devsu.usermicroservice.application.port.out.PersonaIdPostgresGenerator {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String generateId() {
        return jdbcTemplate.queryForObject("select generate_persona_id()", String.class);
    }
}
