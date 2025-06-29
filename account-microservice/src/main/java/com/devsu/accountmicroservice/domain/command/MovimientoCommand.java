package com.devsu.accountmicroservice.domain.command;

import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.domain.model.Movimiento;

import java.time.LocalDateTime;

public abstract class MovimientoCommand {
    protected String id;
    protected Double monto;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public MovimientoCommand(String id, Double monto) {
        this.id = id;
        this.monto = monto;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public abstract Movimiento execute(Cuenta cuenta);
}
