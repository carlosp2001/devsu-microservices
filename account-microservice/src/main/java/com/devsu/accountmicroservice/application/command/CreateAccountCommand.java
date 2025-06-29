package com.devsu.accountmicroservice.application.command;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.CuentaTipoDTO;

public record CreateAccountCommand(Double saldoInicial,
                                   CuentaTipoDTO cuentaTipo,
                                   Boolean estado,
                                   String clientId) {
}
