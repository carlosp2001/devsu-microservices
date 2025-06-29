package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.GetMovimientoByIdUseCase;
import com.devsu.accountmicroservice.domain.exception.TransactionNotFoundException;
import com.devsu.accountmicroservice.domain.model.Movimiento;
import com.devsu.accountmicroservice.infrastructure.mapper.MovimientoMapper;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.MovimientoEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.MovimientoRepository;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.mapper.MovimientoRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMovimientoByIdService implements GetMovimientoByIdUseCase {
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;
    private final MovimientoRestMapper movimientoRestMapper;

    @Override
    public GetMovimientoResponseDTO execute(String movimientoId) {
        MovimientoEntity entity = movimientoRepository.findById(movimientoId).orElseThrow(() -> new TransactionNotFoundException(
                "Movimiento con ID " + movimientoId + " no encontrado"
        ));
        Movimiento domain = movimientoMapper.toDomain(entity);
        return movimientoRestMapper.toResponseDTO(domain);
    }
}
