package com.devsu.usermicroservice.infrastructure.rest.dto;

import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateClientRequestDTO(
        @NotEmpty(message = "Nombre es necesario para actualizar un cliente")
        String nombre,

        @NotNull(message = "El genero es obligatorio para actualizar un cliente")
        PersonaGeneroDTO genero,

        @NotNull(message = "La edad es obligatoria")
        @Min(value = 18, message = "Debe de ser mayor de edad para poder actualizar un cliente")
        Short edad,

        @NotNull(message = "La identificación es obligatoria para actualizar un cliente")
        @NotEmpty(message = "La identificación es obligatoria para actualizar un cliente")
        String identificacion,

        @NotEmpty(message = "El password es obligatorio para actualizar un cliente")
        @NotNull(message = "El password es obligatorio para actualizar un cliente")
        String password,

        @NotNull(message = "El estado es obligatorio para actualizar un cliente")
        Boolean estado,

        String telefono,

        String direccion
) {
}
