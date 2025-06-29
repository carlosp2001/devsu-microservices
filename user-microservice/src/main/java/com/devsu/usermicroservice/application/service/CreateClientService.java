package com.devsu.usermicroservice.application.service;

import com.devsu.usermicroservice.application.command.CreateClientCommand;
import com.devsu.usermicroservice.application.port.in.CreateClientUseCase;
import com.devsu.usermicroservice.application.port.out.ClienteIdPostgresGenerator;
import com.devsu.usermicroservice.application.port.out.PersonaIdPostgresGenerator;
import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.domain.model.enums.PersonaGenero;
import com.devsu.usermicroservice.infrastructure.mapper.ClienteMapper;
import com.devsu.usermicroservice.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.usermicroservice.infrastructure.persistence.repository.ClienteRepository;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.mapper.ClientRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClientService implements CreateClientUseCase {
    private final ClienteIdPostgresGenerator clienteIdPostgresGenerator;
    private final PersonaIdPostgresGenerator personaIdPostgresGenerator;
    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;
    private final ClientRestMapper clientRestMapper;

    @Override
    public CreateClientResponseDTO execute(CreateClientCommand command) {
        String clientId = clienteIdPostgresGenerator.generateId();
        String personId = personaIdPostgresGenerator.generateId();
        Cliente client = new Cliente(clientId,
                personId,
                command.nombre(),
                command.edad(),
                PersonaGenero.valueOf(command.genero().name()),
                command.direccion(),
                command.telefono(),
                command.password());
        ClienteEntity clientEntity = clienteMapper.toEntity(client);
        clienteRepository.save(clientEntity);
        return clientRestMapper.toDto(client);
    }
}
