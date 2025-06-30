package com.devsu.accountmicroservice.infrastructure.rest.dto;

import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.CuentaTipoDTO;

import java.time.LocalDateTime;

public record GetMovimientoReportResponseDTO(
        LocalDateTime fecha,
        String clienteId,
        String cuentaId,
        CuentaTipoDTO tipo,
        Double saldoInicial,
        Boolean estado,
        Double monto,
        Double saldoDisponible
) {
}
