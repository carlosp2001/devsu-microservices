package com.devsu.library.infrastructure.messaging.rabbitmq.dto;

import com.devsu.library.domain.model.Peticion;
import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.CuentaTipoDTO;

import java.io.Serializable;

public record ClientValidationRequestDTO(Peticion request,
                                         Double saldoInicial,
                                         CuentaTipoDTO cuentaTipo,
                                         Boolean activo,
                                         String clientId) implements Serializable {
}
