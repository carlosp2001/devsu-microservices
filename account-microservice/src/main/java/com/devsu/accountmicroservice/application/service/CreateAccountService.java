package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.command.CreateAccountCommand;
import com.devsu.accountmicroservice.application.port.in.AccountCreationUseCase;
import com.devsu.accountmicroservice.application.port.out.ValidateClientUseCase;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.PeticionRepository;
import com.devsu.library.domain.model.enums.PeticionEstado;
import com.devsu.library.infrastructure.mapper.PeticionMapper;
import com.devsu.library.infrastructure.messaging.rabbitmq.dto.enums.ClientValidationRequestDTO;
import com.devsu.library.domain.model.Peticion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements AccountCreationUseCase {
    private final ValidateClientUseCase validateClientUseCase;
    private final PeticionRepository peticionRepository;
    private final PeticionMapper peticionMapper;

    @Override
    public Peticion execute(CreateAccountCommand command) {
        Peticion peticion = new Peticion();
        peticionRepository.save(peticionMapper.toEntity(peticion));
        ClientValidationRequestDTO clientValidationRequest = new ClientValidationRequestDTO(
                peticion,
                command.saldoInicial(),
                command.cuentaTipo(),
                command.estado(),
                command.clientId()
        );
        validateClientUseCase.execute(clientValidationRequest);
        peticion.updateStatus(PeticionEstado.PROCESANDO, "Peticion enviada para validar cliente con ID: " + command.clientId());
        peticionRepository.save(peticionMapper.toEntity(peticion));
        return peticion;
    }
}
