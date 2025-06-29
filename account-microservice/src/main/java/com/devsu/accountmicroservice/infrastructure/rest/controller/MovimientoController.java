package com.devsu.accountmicroservice.infrastructure.rest.controller;

import com.devsu.accountmicroservice.application.port.in.GetMovimientoByIdUseCase;
import com.devsu.accountmicroservice.application.port.in.ProcessMovimientoUseCase;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.ProcessMovimientoRequestDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.ProcessMovimientoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
    private final ProcessMovimientoUseCase processMovimientoUseCase;
    private final GetMovimientoByIdUseCase getMovimientoByIdUseCase;

    @PostMapping
    public ResponseEntity<ProcessMovimientoResponseDTO> createTransaction(@RequestBody @Valid ProcessMovimientoRequestDTO requestDTO) {
        return ResponseEntity.ok(processMovimientoUseCase.execute(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMovimientoResponseDTO> getMovimientoById(@PathVariable("id") String id) {
        return ResponseEntity.ok(getMovimientoByIdUseCase.execute(id));
    }
}
