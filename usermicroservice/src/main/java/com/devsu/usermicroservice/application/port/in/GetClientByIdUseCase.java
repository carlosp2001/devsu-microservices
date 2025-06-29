package com.devsu.usermicroservice.application.port.in;

import com.devsu.usermicroservice.infrastructure.rest.dto.GetClientByIdResponseDTO;

public interface GetClientByIdUseCase {
    GetClientByIdResponseDTO execute(String id);
}
