package com.devsu.usermicroservice.domain.model;

import com.devsu.usermicroservice.domain.model.enums.PeticionEstado;

import java.time.LocalDateTime;
import java.util.UUID;

public class Peticion {
    private UUID peticionId;
    private String mensaje;
    private PeticionEstado estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Peticion() {
        this.peticionId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateStatus(PeticionEstado estado) {
        this.estado = estado;
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getPeticionId() {
        return peticionId;
    }

    public void setMessage(String mensaje) {
        this.mensaje = mensaje;
        this.updatedAt = LocalDateTime.now();
    }
}
