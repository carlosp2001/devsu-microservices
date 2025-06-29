package com.devsu.usermicroservice.application.service;

import com.devsu.usermicroservice.application.command.UpdateClientCommand;
import com.devsu.usermicroservice.application.port.in.UpdateClientUseCase;
import com.devsu.usermicroservice.domain.exception.ClientNotFoundException;
import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import com.devsu.usermicroservice.infrastructure.mapper.ClienteMapper;
import com.devsu.usermicroservice.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.usermicroservice.infrastructure.persistence.repository.ClientRepository;
import com.devsu.usermicroservice.infrastructure.rest.dto.UpdateClientResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.mapper.ClientRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateClientService implements UpdateClientUseCase {
    private final ClientRepository clientRepository;
    private final ClienteMapper clienteMapper;
    private final ClientRestMapper clientRestMapper;

    @Override
    public UpdateClientResponseDTO execute(UpdateClientCommand command) {
        ClienteEntity clientEntity = clientRepository.findById(command.id())
                .orElseThrow(() ->
                        new ClientNotFoundException("Cliente no encontrado con ID: " + command.id()));

        Cliente clienteDomain = clienteMapper.toDomain(clientEntity);
        clienteDomain.update(
                command.nombre(),
                command.edad(),
                PersonaGenero.valueOf(command.genero().name()),
                command.direccion(),
                command.telefono(),
                command.estado(),
                command.password()
        );

        clientRepository.save(clienteMapper.toEntity(clienteDomain));
        return clientRestMapper.toUpdateDto(clienteDomain);
    }
}
