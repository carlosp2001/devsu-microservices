package com.devsu.usermicroservice.domain.model;


import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;

import java.time.LocalDateTime;

public class Persona {

    private final String id;
    private String nombre;
    private Short edad;
    private PersonaGenero genero;
    private String direccion;
    private String identificacion;
    private String telefono;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Persona(String id, String nombre, short edad, PersonaGenero genero, String direccion, String identificacion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    public String getPersonId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Short getEdad() {
        return edad;
    }

    public PersonaGenero getGenero() {
        return genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDateTime getPersonCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPersonUpdatedAt() {
        return updatedAt;
    }
}
