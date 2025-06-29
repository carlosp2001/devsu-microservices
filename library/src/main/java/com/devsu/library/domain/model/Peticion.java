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
}
