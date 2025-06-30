package com.devsu.accountmicroservice.infrastructure.persistence.repository;

import com.devsu.accountmicroservice.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, String> {
    @Query("""
        SELECT m FROM MovimientoEntity m
        WHERE m.cuentaEntity.clienteId = :clientId
          AND m.createdAt BETWEEN :startDate AND :endDate
    """)
    Page<MovimientoEntity> findAllByClientAndDateBetween(
            @Param("clientId") String clientId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}
