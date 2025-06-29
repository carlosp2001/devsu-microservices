package com.devsu.accountmicroservice.infrastructure.rest.dto;

import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.CuentaTipoDTO;

import java.time.LocalDateTime;

public record GetAccountByIdResponseDTO(
        String id,
        Double saldo,
        CuentaTipoDTO tipo,
        Boolean estado,
        String clienteId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
