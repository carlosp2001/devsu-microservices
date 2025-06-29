package com.devsu.accountmicroservice.application.port.in;

import com.devsu.accountmicroservice.infrastructure.rest.dto.ProcessMovimientoRequestDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.ProcessMovimientoResponseDTO;

public interface ProcessMovimientoUseCase {
    ProcessMovimientoResponseDTO execute(ProcessMovimientoRequestDTO request);
}
