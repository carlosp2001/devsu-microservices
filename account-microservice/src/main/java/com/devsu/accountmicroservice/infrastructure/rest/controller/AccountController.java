package com.devsu.accountmicroservice.infrastructure.rest.controller;

import com.devsu.accountmicroservice.application.command.CreateAccountCommand;
import com.devsu.accountmicroservice.application.port.in.AccountCreationUseCase;
import com.devsu.accountmicroservice.infrastructure.rest.dto.CreateAccountRequestDTO;
import com.devsu.library.domain.model.Peticion;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class AccountController {
    private final AccountCreationUseCase accountCreationUseCase;

    @PostMapping
    public ResponseEntity<Peticion> createAccount(@RequestBody @Valid CreateAccountRequestDTO requestDTO) {
        CreateAccountCommand command = new CreateAccountCommand(
                requestDTO.saldoInicial(),
                requestDTO.cuentaTipo(),
                requestDTO.estado(),
                requestDTO.clientId());
        return ResponseEntity.ok(accountCreationUseCase.execute(command));
    }
}
