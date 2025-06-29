package com.devsu.accountmicroservice.domain.command;

import com.devsu.accountmicroservice.domain.exception.InsufficientBalanceException;
import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.domain.model.Movimiento;
import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;

import java.math.BigDecimal;

public class RetiroCommand extends MovimientoCommand {
    public RetiroCommand(String id, Double monto) {
        super(id, monto);
    }

    @Override
    public Movimiento execute(Cuenta cuenta) {
        if (cuenta.getSaldo().compareTo(monto) >= 0) {
            BigDecimal saldo = BigDecimal.valueOf(cuenta.getSaldo());
            BigDecimal montoDecimal = BigDecimal.valueOf(this.monto);
            cuenta.setSaldo(saldo.subtract(montoDecimal).doubleValue());
            Movimiento movimiento = new Movimiento(id, MovimientoTipo.RETIRO, monto, cuenta.getId());
            return movimiento;
        } else {
            throw new InsufficientBalanceException("Saldo no disponible para realizar el retiro");
        }
    }
}
