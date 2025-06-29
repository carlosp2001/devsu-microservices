package com.devsu.accountmicroservice.application.command;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.CuentaTipoDTO;

public record UpdateAccountCommand(String accountId,
                                   Boolean estado) {
}
