package com.devsu.accountmicroservice.infrastructure.rest.controller;

import com.devsu.accountmicroservice.application.command.CreateAccountCommand;
import com.devsu.accountmicroservice.application.port.in.CreateAccountUseCase;
import com.devsu.accountmicroservice.application.port.in.GetPeticionStatusUseCase;
import com.devsu.accountmicroservice.application.service.GetPeticionStatusService;
import com.devsu.accountmicroservice.infrastructure.rest.dto.CreateAccountRequestDTO;
import com.devsu.library.domain.model.Peticion;
import com.devsu.library.domain.model.enums.PeticionEstado;
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

}
