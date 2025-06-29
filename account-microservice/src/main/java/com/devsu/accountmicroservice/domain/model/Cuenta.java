package com.devsu.accountmicroservice.domain.model;

import com.devsu.accountmicroservice.domain.command.MovimientoCommand;
import com.devsu.accountmicroservice.domain.model.enums.CuentaTipo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cuenta {
    private String id;
    @Setter
    private Double saldo;
    private CuentaTipo tipo;
    private Boolean estado;
    private String clienteId;
    List<Movimiento> movimientos = new ArrayList<>();
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

    public Movimiento addTransaction(MovimientoCommand command) {
        Movimiento movimiento = command.execute(this);
        this.updatedAt = LocalDateTime.now();
        return movimiento;
    }
}
