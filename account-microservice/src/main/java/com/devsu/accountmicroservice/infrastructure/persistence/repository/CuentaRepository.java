package com.devsu.accountmicroservice.infrastructure.persistence.repository;

import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, String> {
}
