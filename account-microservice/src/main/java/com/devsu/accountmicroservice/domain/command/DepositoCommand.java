package com.devsu.accountmicroservice.domain.command;

import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.domain.model.Movimiento;
import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;

import java.math.BigDecimal;

public class DepositoCommand extends MovimientoCommand {
    public DepositoCommand(String id, Double monto) {
        super(id, monto);
    }

    @Override
    public Movimiento execute(Cuenta account) {
        BigDecimal saldo = BigDecimal.valueOf(account.getSaldo());
        BigDecimal montoDecimal = BigDecimal.valueOf(this.monto);
        account.setSaldo(saldo.add(montoDecimal).doubleValue());
        Movimiento movimiento = new Movimiento(
                id,
                MovimientoTipo.DEPOSITO,
                monto,
                account.getId());
        return movimiento;
    }
}
