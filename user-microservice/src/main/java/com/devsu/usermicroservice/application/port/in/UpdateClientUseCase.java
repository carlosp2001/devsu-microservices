package com.devsu.usermicroservice.application.port.in;

import com.devsu.usermicroservice.application.command.UpdateClientCommand;
import com.devsu.usermicroservice.infrastructure.rest.dto.UpdateClientResponseDTO;

public interface UpdateClientUseCase {
    UpdateClientResponseDTO execute(UpdateClientCommand command);
}
