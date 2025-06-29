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
                   String telefono,
                   String password) {
        super(personaId, nombre, edad, genero, direccion, telefono);
        this.id = id;
        this.estado = true;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private Cliente(String id,
                    String personId,
                    String nombre,
                    short edad,
                    PersonaGenero genero,
                    String direccion,
                    String telefono,
                    Boolean estado,
                    String password,
                    LocalDateTime personCreatedAt,
                    LocalDateTime personUpdatedAt,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        super(personId, nombre, edad, genero, direccion, telefono, personCreatedAt, personUpdatedAt);
        this.id = id;
        this.estado = estado;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Cliente hydrate(String id,
                                  String personId,
                                  String nombre,
                                  short edad,
                                  PersonaGenero genero,
                                  String direccion,
                                  String telefono,
                                  Boolean estado,
                                  String password,
                                  LocalDateTime personCreatedAt,
                                  LocalDateTime personUpdatedAt,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
        return new Cliente(id, personId, nombre, edad, genero, direccion, telefono, estado, password, personCreatedAt, personUpdatedAt, createdAt, updatedAt);
    }

    public void update(String nombre,
                       Short edad,
                       PersonaGenero genero,
                       String direccion,
                       String telefono,
                       Boolean estado,
                       String password) {
        this.updatePerson(nombre, edad, genero, direccion, telefono);
        this.estado = estado;
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }
}
