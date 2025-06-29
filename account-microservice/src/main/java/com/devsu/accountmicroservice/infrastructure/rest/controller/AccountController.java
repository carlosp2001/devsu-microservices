package com.devsu.accountmicroservice.infrastructure.rest.controller;

import com.devsu.accountmicroservice.application.command.CreateAccountCommand;
import com.devsu.accountmicroservice.application.command.UpdateAccountCommand;
import com.devsu.accountmicroservice.application.port.in.*;
import com.devsu.accountmicroservice.infrastructure.rest.dto.CreateAccountRequestDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetAccountByIdResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.UpdateAccountRequestDTO;
import com.devsu.library.domain.model.Peticion;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final GetPeticionStatusUseCase getPeticionStatusUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @PostMapping
    public ResponseEntity<Peticion> createAccount(@RequestBody @Valid CreateAccountRequestDTO requestDTO) {
        CreateAccountCommand command = new CreateAccountCommand(
                requestDTO.saldoInicial(),
                requestDTO.cuentaTipo(),
                requestDTO.estado(),
                requestDTO.clientId());
        return ResponseEntity.ok(createAccountUseCase.execute(command));
    }

    @GetMapping("/{peticionId}/estado")
    public ResponseEntity<Peticion> getPeticionEstado(@PathVariable("peticionId")
                                                      UUID peticionId) {
        return ResponseEntity.ok(getPeticionStatusUseCase.execute(peticionId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<GetAccountByIdResponseDTO> getAccountById(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(getAccountByIdUseCase.execute(accountId));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") String accountId) {
        deleteAccountUseCase.execute(accountId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable("accountId") String accountId, @RequestBody @Valid UpdateAccountRequestDTO requestDTO) {
        UpdateAccountCommand command = new UpdateAccountCommand(
                accountId,
                requestDTO.estado());
        updateAccountUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
