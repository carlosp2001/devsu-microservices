package com.devsu.usermicroservice.application.command;

import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;

public record UpdateClientCommand(
        String id,
        String nombre,
        PersonaGeneroDTO genero,
        Short edad,
        Boolean estado,
        String telefono,
        String direccion,
        String identificacion,
        String password) {
}
