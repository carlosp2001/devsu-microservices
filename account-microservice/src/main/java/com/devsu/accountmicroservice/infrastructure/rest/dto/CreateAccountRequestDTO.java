package com.devsu.accountmicroservice.infrastructure.rest.dto;

import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.CuentaTipoDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequestDTO(
        @DecimalMin(value = "100.0", inclusive = false, message = "Saldo inicial debe ser mayor a 100")
        @NotNull(message = "Saldo inicial no puede ser nulo")
        Double saldoInicial,

        @NotNull(message = "Tipo de cuenta no puede ser nulo")
        CuentaTipoDTO cuentaTipo,

        @NotNull(message = "Estado no puede ser nulo")
        Boolean estado,

        @NotBlank(message = "ID del cliente no puede estar vacío")
        @NotNull(message = "ID del cliente no puede ser nulo")
        String clientId
) {
}
