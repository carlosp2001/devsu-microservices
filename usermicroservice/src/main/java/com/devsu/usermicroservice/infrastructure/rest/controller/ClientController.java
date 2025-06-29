package com.devsu.usermicroservice.infrastructure.rest.controller;

import com.devsu.usermicroservice.application.command.CreateClientCommand;
import com.devsu.usermicroservice.application.command.UpdateClientCommand;
import com.devsu.usermicroservice.application.port.in.CreateClientUseCase;
import com.devsu.usermicroservice.application.port.in.DeleteClientUseCase;
import com.devsu.usermicroservice.application.port.in.GetClientByIdUseCase;
import com.devsu.usermicroservice.application.port.in.UpdateClientUseCase;
import com.devsu.usermicroservice.infrastructure.rest.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClientController {
    private final CreateClientUseCase createClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final GetClientByIdUseCase getClientByIdUseCase;

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> createClient(@RequestBody @Valid CreateClientRequestDTO requestDTO) {
        CreateClientCommand createClientCommand = new CreateClientCommand(
                requestDTO.nombre(),
                requestDTO.genero(),
                requestDTO.edad(),
                requestDTO.telefono(),
                requestDTO.direccion(),
                requestDTO.password()
        );
        return ResponseEntity.ok(createClientUseCase.execute(createClientCommand));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<GetClientByIdResponseDTO> getClientById(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(getClientByIdUseCase.execute(clientId));
    }


    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") String clientId) {
        deleteClientUseCase.execute(clientId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<UpdateClientResponseDTO> updateClient(@PathVariable("clientId") String clientId,
                                                                @RequestBody @Valid UpdateClientRequestDTO
                                                                        requestDTO) {
        UpdateClientCommand updateClientCommand = new UpdateClientCommand(
                clientId,
                requestDTO.nombre(),
                requestDTO.genero(),
                requestDTO.edad(),
                requestDTO.estado(),
                requestDTO.telefono(),
                requestDTO.direccion(),
                requestDTO.identificacion(),
                requestDTO.password()
        );
        return ResponseEntity.ok(updateClientUseCase.execute(updateClientCommand));
    }
}
