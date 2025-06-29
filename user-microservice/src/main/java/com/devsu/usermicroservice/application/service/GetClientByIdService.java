package com.devsu.usermicroservice.application.service;

import com.devsu.usermicroservice.application.port.in.GetClientByIdUseCase;
import com.devsu.usermicroservice.domain.exception.ClientNotFoundException;
import com.devsu.usermicroservice.domain.model.Cliente;
import com.devsu.usermicroservice.infrastructure.mapper.ClienteMapper;
import com.devsu.usermicroservice.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.usermicroservice.infrastructure.persistence.repository.ClientRepository;
import com.devsu.usermicroservice.infrastructure.rest.dto.GetClientByIdResponseDTO;
import com.devsu.usermicroservice.infrastructure.rest.mapper.ClientRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetClientByIdService implements GetClientByIdUseCase {
    private final ClientRepository clientRepository;
    private final ClientRestMapper clientRestMapper;
    private final ClienteMapper clienteMapper;

    @Override
    public GetClientByIdResponseDTO execute(String id) {
        ClienteEntity clientEntity = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + id));
        Cliente clienteDomain = clienteMapper.toDomain(clientEntity);
        return clientRestMapper.toGetResponseDto(clienteDomain);
    }
}
