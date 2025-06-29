package com.devsu.usermicroservice.application.service;

import com.devsu.usermicroservice.application.port.in.DeleteClientUseCase;
import com.devsu.usermicroservice.domain.exception.ClientNotFoundException;
import com.devsu.usermicroservice.infrastructure.persistence.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteClientService implements DeleteClientUseCase {
    private final ClientRepository clientRepository;

    @Override
    public void execute(String clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
        } else {
            throw new ClientNotFoundException("Cliente con ID " + clientId + " no encontrado");
        }
    }
}
