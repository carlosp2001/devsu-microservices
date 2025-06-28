package com.devsu.usermicroservice.application.command;

import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;

public record CreateClientCommand(
        String nombre,
        PersonaGeneroDTO genero,
        short edad,
        String telefono,
        String direccion,
        String identificacion,
        String password) {
}
