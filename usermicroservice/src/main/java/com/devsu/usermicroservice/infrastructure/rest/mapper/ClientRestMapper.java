package com.devsu.usermicroservice.infrastructure.rest.mapper;

import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.dto.enums.PersonaGeneroDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientRestMapper {
    public CreateClientResponseDTO toDto(Cliente domain) {
        return new CreateClientResponseDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getEdad(),
                toDto(domain.getGenero()),
                domain.getDireccion(),
                domain.getTelefono(),
                domain.getEstado(),
                domain.getPassword(),
                domain.getCreatedAt(),
                domain.getCreatedAt()
        );
    }

    public PersonaGeneroDTO toDto(PersonaGenero domain) {
        if (domain == null) {
            return null;
        }
        return switch (domain) {
            case MASCULINO -> PersonaGeneroDTO.MASCULINO;
            case FEMENINO -> PersonaGeneroDTO.FEMENINO;
        };
    }
}
