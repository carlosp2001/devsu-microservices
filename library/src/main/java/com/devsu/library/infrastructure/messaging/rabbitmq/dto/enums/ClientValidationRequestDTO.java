package com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums;

import com.devsu.library.domain.model.Peticion;

import java.io.Serializable;

public record ClientValidationRequestDTO(Peticion request,
                                         Double saldoInicial,
                                         CuentaTipoDTO cuentaTipo,
                                         Boolean activo,
                                         String clientId) implements Serializable {
}
