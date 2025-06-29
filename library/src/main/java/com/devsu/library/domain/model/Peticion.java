package com.devsu.library.domain.model;

import com.devsu.library.domain.model.enums.PeticionEstado;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Peticion implements Serializable {
    private UUID peticionId;
    private String mensaje;
    private PeticionEstado estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Peticion() {
        this.peticionId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.mensaje = "Pendiente de ser procesada";
        this.estado = PeticionEstado.PENDIENTE;
    }

    public void updateStatus(PeticionEstado estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.updatedAt = LocalDateTime.now();
    }

    private Peticion(UUID peticionId, String mensaje, PeticionEstado estado, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.peticionId = peticionId;
        this.mensaje = mensaje;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Peticion hydrate(UUID peticionId, String mensaje, PeticionEstado estado, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Peticion(peticionId, mensaje, estado, createdAt, updatedAt);
    }
}
