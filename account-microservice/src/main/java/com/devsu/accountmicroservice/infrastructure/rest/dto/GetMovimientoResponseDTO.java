package com.devsu.accountmicroservice.infrastructure.rest.dto;

import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.MovimientoTipoDTO;

import java.time.LocalDateTime;

public record GetMovimientoResponseDTO(
        String id,
        MovimientoTipoDTO tipo,
        String cuentaId,
        Double monto,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
