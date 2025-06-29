package com.devsu.accountmicroservice.application.port.in;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.ClientValidationRequestDTO;

public interface HandleAccountCreationUseCase {
    void execute(ClientValidationRequestDTO request);
}
