package com.devsu.accountmicroservice.application.port.out;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.ClientValidationRequestDTO;

public interface ValidateClientUseCase {
    void execute(ClientValidationRequestDTO request);
}
