package com.devsu.usermicroservice.infrastructure.rest.dto;

import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateClientRequestDTO(
        @NotEmpty(message = "Nombre es necesario para crear un cliente")
        String nombre,

        @NotNull(message = "El genero es obligatorio para crear un cliente")
        PersonaGeneroDTO genero,

        @NotNull(message = "La edad es obligatoria")
        @Min(value = 18, message = "Debe de ser mayor de edad para poder crear un cliente")
        Short edad,

        @NotNull(message = "El identificacion es obligatorio para crear un cliente")
        @NotEmpty(message = "El identificacion es obligatorio para crear un cliente")
        String identificacion,

        @NotEmpty(message = "El password es obligatorio para crear un cliente")
        @NotNull(message = "El password es obligatorio para crear un cliente")
        String password,

        String telefono,

        String direccion
) {
}
