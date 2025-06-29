package com.devsu.accountmicroservice.infrastructure.rest.dto;

import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessMovimientoRequestDTO(
        @NotNull(message = "Tipo de movimiento no puede ser nulo")
        MovimientoTipo tipo,
        @NotNull(message = "ID de cuenta no puede ser nulo")
        @NotBlank(message = "ID de cuenta no puede estar vacío")
        String cuentaId,
        @NotNull(message = "Monto no puede ser nulo")
        @Min(value = 0, message = "Monto debe ser mayor o igual a 0")
        Double monto
) {
}
