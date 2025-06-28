package com.devsu.usermicroservice.infrastructure.rest.controller;

import com.devsu.usermicroservice.application.command.CreateClientCommand;
import com.devsu.usermicroservice.application.port.in.CreateClientUseCase;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientRequestDTO;
import com.devsu.usermicroservice.infrastructure.rest.dto.CreateClientResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClientController {
    private final CreateClientUseCase createClientUseCase;

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> createClient(@RequestBody @Valid CreateClientRequestDTO requestDTO) {
        CreateClientCommand createClientCommand = new CreateClientCommand(
                requestDTO.nombre(),
                requestDTO.genero(),
                requestDTO.edad(),
                requestDTO.telefono(),
                requestDTO.direccion(),
                requestDTO.identificacion(),
                requestDTO.password()
        );
        return ResponseEntity.ok(createClientUseCase.execute(createClientCommand));
    }
}
