package com.devsu.usermicroservice.application.port.in;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.ClientValidationRequestDTO;

public interface ClientValidationUseCase {
    void execute(ClientValidationRequestDTO request);
}
