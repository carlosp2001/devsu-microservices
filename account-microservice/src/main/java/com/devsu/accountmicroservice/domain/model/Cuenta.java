package com.devsu.accountmicroservice.domain.model;

import com.devsu.accountmicroservice.domain.model.enums.CuentaTipo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Cuenta {
    private String id;
    private Double saldoInicial;
    private CuentaTipo tipo;
    private Boolean estado;
    private String clienteId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cuenta(String id, Double saldoInicial, CuentaTipo tipo, String clienteId) {
        this.id = id;
        this.saldoInicial = saldoInicial;
        this.tipo = tipo;
        this.estado = true;
        this.clienteId = clienteId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
