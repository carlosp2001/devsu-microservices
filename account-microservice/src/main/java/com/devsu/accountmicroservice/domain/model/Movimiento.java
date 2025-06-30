package com.devsu.accountmicroservice.domain.model;

import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Movimiento {
    private String id;
    private MovimientoTipo tipo;
    private String cuentaId;
    private Double monto;
    private Double saldoInicial;
    private Double saldoDisponible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Movimiento(String id, MovimientoTipo tipo, Double monto, String cuentaId, Double saldoInicial, Double saldoDisponible) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaId = cuentaId;
        this.saldoInicial = saldoInicial;
        this.saldoDisponible = saldoDisponible;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private Movimiento(String id, MovimientoTipo tipo, String cuentaId, Double monto, LocalDateTime createdAt, LocalDateTime updatedAt, Double saldoInicial, Double saldoDisponible) {
        this.id = id;
        this.tipo = tipo;
        this.cuentaId = cuentaId;
        this.monto = monto;
        this.saldoInicial = saldoInicial;
        this.saldoDisponible = saldoDisponible;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Movimiento hydrate(String id, MovimientoTipo tipo, String cuentaId, Double monto, LocalDateTime createdAt, LocalDateTime updatedAt, Double saldoInicial, Double saldoDisponible) {
        return new Movimiento(id, tipo, cuentaId, monto, createdAt, updatedAt, saldoInicial, saldoDisponible);
    }
}
