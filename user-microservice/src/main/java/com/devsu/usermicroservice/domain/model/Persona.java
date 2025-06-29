package com.devsu.usermicroservice.domain.model;


import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Persona {

    private final String id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private Short edad;
    @Getter
    @Setter
    private PersonaGenero genero;
    @Getter
    @Setter
    private String direccion;

    @Getter
    @Setter
    private String telefono;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Persona(String id, String nombre, short edad, PersonaGenero genero, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getPersonId() {
        return id;
    }

    public LocalDateTime getPersonCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPersonUpdatedAt() {
        return updatedAt;
    }

    protected Persona(String id,
                      String nombre,
                      Short edad,
                      PersonaGenero genero,
                      String direccion,
                      String telefono,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void updatePerson(String nombre, Short edad, PersonaGenero genero, String direccion, String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.updatedAt = LocalDateTime.now();
    }
}
