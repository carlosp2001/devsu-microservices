package com.devsu.usermicroservice.application.port.in;

import com.devsu.usermicroservice.application.command.CreateClientCommand;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientResponseDTO;

public interface CreateClientUseCase {
    CreateClientResponseDTO execute(CreateClientCommand command);
}
