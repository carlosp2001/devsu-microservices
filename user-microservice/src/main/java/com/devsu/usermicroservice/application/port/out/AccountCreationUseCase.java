package com.devsu.usermicroservice.application.port.out;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.ClientValidationRequestDTO;

public interface AccountCreationUseCase {
    void execute(ClientValidationRequestDTO requestDTO);
}
