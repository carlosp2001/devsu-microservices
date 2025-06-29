package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.DeleteMovimientoByIdUseCase;
import com.devsu.accountmicroservice.domain.exception.TransactionNotFoundException;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMovimientoByIdService implements DeleteMovimientoByIdUseCase {
    private final MovimientoRepository movimientoRepository;

    @Override
    public void execute(String movimientoId) {
        if (!movimientoRepository.existsById(movimientoId)) {
            throw new TransactionNotFoundException("Movimiento con ID " + movimientoId + " no encontrado");
        }
        movimientoRepository.deleteById(movimientoId);
    }
}
