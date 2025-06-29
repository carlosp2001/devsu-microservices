package com.devsu.usermicroservice.infrastructure.rest.dto;

import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;

import java.time.LocalDateTime;

public record GetClientByIdResponseDTO(
        String id,
        String nombre,
        Short edad,
        PersonaGeneroDTO genero,
        String direccion,
        String telefono,
        Boolean estado,
        String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
