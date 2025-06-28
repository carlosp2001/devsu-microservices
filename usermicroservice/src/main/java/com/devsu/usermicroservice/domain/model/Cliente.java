package com.devsu.usermicroservice.domain.model;


import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Cliente extends Persona {
    private String id;
    private Boolean estado;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cliente(String id,
                   String personaId,
                   String nombre,
                   Short edad,
                   PersonaGenero genero,
                   String direccion,
                   String identificacion,
                   String telefono,
                   String password) {
        super(personaId, nombre, edad, genero, direccion, identificacion, telefono);
        this.id = id;
        this.estado = true;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
