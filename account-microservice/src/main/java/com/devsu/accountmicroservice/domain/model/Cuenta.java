package com.devsu.accountmicroservice.domain.model;

import com.devsu.accountmicroservice.domain.model.enums.CuentaTipo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Cuenta {
    private String id;
    private Double saldo;
    private CuentaTipo tipo;
    private Boolean estado;
    private String clienteId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cuenta(String id, Double saldoInical, CuentaTipo tipo, String clienteId) {
        this.id = id;
        this.saldo = saldoInical;
        this.tipo = tipo;
        this.estado = true;
        this.clienteId = clienteId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private Cuenta(String id, Double saldo, CuentaTipo tipo, Boolean estado, String clienteId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.saldo = saldo;
        this.tipo = tipo;
        this.estado = estado;
        this.clienteId = clienteId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Cuenta hydrate(String id, Double saldo, CuentaTipo tipo, Boolean estado, String clienteId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Cuenta(id, saldo, tipo, estado, clienteId, createdAt, updatedAt);
    }

    public void update(Boolean estado) {
        this.estado = estado;
        this.updatedAt = LocalDateTime.now();
    }
}
