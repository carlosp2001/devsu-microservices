package com.devsu.usermicroservice.infrastructure.rest.mapper;

import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.dto.GetClientByIdResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.dto.UpdateClientResponseDTO;
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

    public UpdateClientResponseDTO toUpdateDto(Cliente domain) {
        return new UpdateClientResponseDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getEdad(),
                toDto(domain.getGenero()),
                domain.getDireccion(),
                domain.getTelefono(),
                domain.getEstado(),
                domain.getPassword(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public GetClientByIdResponseDTO toGetResponseDto(Cliente domain) {
        return new GetClientByIdResponseDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getEdad(),
                toDto(domain.getGenero()),
                domain.getDireccion(),
                domain.getTelefono(),
                domain.getEstado(),
                domain.getPassword(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
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
