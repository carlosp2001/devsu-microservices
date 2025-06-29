package com.devsu.accountmicroservice.application.port.in;

import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoResponseDTO;

public interface GetMovimientoByIdUseCase {
    GetMovimientoResponseDTO execute(String movimientoId);
}
