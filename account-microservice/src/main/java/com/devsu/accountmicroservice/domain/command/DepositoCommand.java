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
        BigDecimal initialBalance = BigDecimal.valueOf(account.getSaldo());
        BigDecimal montoDecimal = BigDecimal.valueOf(this.monto);
        Double newBalance = initialBalance.add(montoDecimal).doubleValue();
        account.setSaldo(newBalance);
        Movimiento movimiento = new Movimiento(
                id,
                MovimientoTipo.DEPOSITO,
                monto,
                account.getId(),
                initialBalance.doubleValue(),
                newBalance);
        return movimiento;
    }
}
