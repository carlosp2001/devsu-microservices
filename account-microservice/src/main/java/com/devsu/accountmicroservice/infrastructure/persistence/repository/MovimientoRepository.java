package com.devsu.accountmicroservice.infrastructure.persistence.repository;

import com.devsu.accountmicroservice.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, String> {
}
