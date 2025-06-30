package com.devsu.accountmicroservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
@Setter
@Getter
public class MovimientoEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaEntity cuentaEntity;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private Double saldoInicial;

    @Column(nullable = false)
    private Double saldoDisponible;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
