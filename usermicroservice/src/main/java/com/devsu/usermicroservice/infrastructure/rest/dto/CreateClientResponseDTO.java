package com.devsu.usermicroservice.infrastructure.rest.dto;

import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;

import java.time.LocalDateTime;

public record CreateClientResponseDTO(
        String id,
        String nombre,
        short edad,
        PersonaGeneroDTO genero,
        String direccion,
        String telefono,
        boolean estado,
        String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
