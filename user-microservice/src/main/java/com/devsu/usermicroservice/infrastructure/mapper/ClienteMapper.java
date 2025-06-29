package com.devsu.usermicroservice.infrastructure.mapper;

import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import com.devsu.usermicroservice.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.usermicroservice.infrastructure.persistence.entity.PersonaEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteEntity toEntity(com.devsu.usermicroservice.domain.model.Cliente client) {
        PersonaEntity person = new PersonaEntity();
        person.setId(client.getPersonId());
        person.setNombre(client.getNombre());
        person.setEdad(client.getEdad());
        person.setGenero(client.getGenero().name());
        person.setDireccion(client.getDireccion());
        person.setTelefono(client.getTelefono());
        person.setCreatedAt(client.getPersonCreatedAt());
        person.setUpdatedAt(client.getPersonUpdatedAt());

        ClienteEntity clientEntity = new ClienteEntity();
        clientEntity.setId(client.getId());
        clientEntity.setPersonaEntity(person);
        clientEntity.setPassword(client.getPassword());
        clientEntity.setEstado(client.getEstado());
        clientEntity.setCreatedAt(client.getCreatedAt());
        clientEntity.setUpdatedAt(client.getUpdatedAt());

        return clientEntity;
    }

    public Cliente toDomain(ClienteEntity clientEntity) {
        Cliente client = Cliente.hydrate(
                clientEntity.getId(),
                clientEntity.getPersonaEntity().getId(),
                clientEntity.getPersonaEntity().getNombre(),
                clientEntity.getPersonaEntity().getEdad(),
                PersonaGenero.valueOf(clientEntity.getPersonaEntity().getGenero()),
                clientEntity.getPersonaEntity().getDireccion(),
                clientEntity.getPersonaEntity().getTelefono(),
                clientEntity.getEstado(),
                clientEntity.getPassword(),
                clientEntity.getPersonaEntity().getCreatedAt(),
                clientEntity.getPersonaEntity().getUpdatedAt(),
                clientEntity.getCreatedAt(),
                clientEntity.getUpdatedAt()
        );

        return client;
    }
}
