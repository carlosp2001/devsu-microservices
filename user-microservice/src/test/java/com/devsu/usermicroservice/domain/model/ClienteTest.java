package com.devsu.usermicroservice.domain.model;

import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClienteTest {
    @Test
    public void testConstructor() {
        String id = "123";
        String personaId = "456";
        String nombre = "John Doe";
        Short edad = 30;
        PersonaGenero genero = PersonaGenero.MASCULINO;
        String direccion = "123 Main St";
        String telefono = "1234567890";
        String password = "password";

        Cliente cliente = new Cliente(id, personaId, nombre, edad, genero, direccion, telefono, password);

        assertThat(cliente.getId()).isEqualTo(id);
        assertThat(cliente.getPersonId()).isEqualTo(personaId);
        assertThat(cliente.getNombre()).isEqualTo(nombre);
        assertThat(cliente.getEdad()).isEqualTo(edad);
        assertThat(cliente.getGenero()).isEqualTo(genero);
        assertThat(cliente.getDireccion()).isEqualTo(direccion);
        assertThat(cliente.getTelefono()).isEqualTo(telefono);
        assertThat(cliente.getEstado()).isTrue();
        assertThat(cliente.getPassword()).isEqualTo(password);
        assertThat(cliente.getCreatedAt()).isNotNull();
        assertThat(cliente.getUpdatedAt()).isNotNull();
    }

    @Test
    public void testHydrate() {
        String id = "123";
        String personId = "456";
        String nombre = "John Doe";
        short edad = 30;
        PersonaGenero genero = PersonaGenero.MASCULINO;
        String direccion = "123 Main St";
        String telefono = "1234567890";
        Boolean estado = true;
        String password = "password";
        LocalDateTime personCreatedAt = LocalDateTime.now();
        LocalDateTime personUpdatedAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Cliente cliente = Cliente.hydrate(id, personId, nombre, edad, genero, direccion, telefono,
                estado, password, personCreatedAt, personUpdatedAt, createdAt, updatedAt);

        // Then
        assertThat(cliente.getId()).isEqualTo(id);
        assertThat(cliente.getPersonId()).isEqualTo(personId);
        assertThat(cliente.getNombre()).isEqualTo(nombre);
        assertThat(cliente.getEdad()).isEqualTo(edad);
        assertThat(cliente.getGenero()).isEqualTo(genero);
        assertThat(cliente.getDireccion()).isEqualTo(direccion);
        assertThat(cliente.getTelefono()).isEqualTo(telefono);
        assertThat(cliente.getEstado()).isEqualTo(estado);
        assertThat(cliente.getPassword()).isEqualTo(password);
        assertThat(cliente.getPersonCreatedAt()).isEqualTo(personCreatedAt);
        assertThat(cliente.getPersonUpdatedAt()).isEqualTo(personUpdatedAt);
        assertThat(cliente.getCreatedAt()).isEqualTo(createdAt);
        assertThat(cliente.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    public void testUpdate() {
        String id = "123";
        String personaId = "456";
        String nombre = "John Doe";
        Short edad = 30;
        PersonaGenero genero = PersonaGenero.MASCULINO;
        String direccion = "123 Main St";
        String telefono = "1234567890";
        String password = "password";
        Cliente cliente = new Cliente(id, personaId, nombre, edad, genero, direccion, telefono, password);

        String nuevoNombre = "Jane Doe";
        Short nuevaEdad = 35;
        PersonaGenero nuevoGenero = PersonaGenero.FEMENINO;
        String nuevaDireccion = "456 Elm St";
        String nuevoTelefono = "0987654321";
        Boolean nuevoEstado = false;
        String nuevoPassword = "newPassword";
        cliente.update(nuevoNombre, nuevaEdad, nuevoGenero, nuevaDireccion, nuevoTelefono, nuevoEstado, nuevoPassword);

        assertThat(cliente.getNombre()).isEqualTo(nuevoNombre);
        assertThat(cliente.getEdad()).isEqualTo(nuevaEdad);
        assertThat(cliente.getGenero()).isEqualTo(nuevoGenero);
        assertThat(cliente.getDireccion()).isEqualTo(nuevaDireccion);
        assertThat(cliente.getTelefono()).isEqualTo(nuevoTelefono);
        assertThat(cliente.getEstado()).isEqualTo(nuevoEstado);
        assertThat(cliente.getPassword()).isEqualTo(nuevoPassword);
        assertThat(cliente.getUpdatedAt()).isNotEqualTo(cliente.getCreatedAt());
    }
}
