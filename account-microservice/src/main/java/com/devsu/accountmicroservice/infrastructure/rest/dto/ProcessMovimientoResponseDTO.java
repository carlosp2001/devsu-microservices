package com.devsu.accountmicroservice.infrastructure.rest.dto;

import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.CuentaTipoDTO;

public record ProcessMovimientoResponseDTO(
        String id,
        Double saldoInicial,
        String cuentaId,
        CuentaTipoDTO cuentaTipo,
        Boolean estado,
        Double monto,
        Double saldoDisponible) {
}
