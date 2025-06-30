package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.ProcessMovimientoUseCase;
import com.devsu.accountmicroservice.application.port.out.MovimientoIdPostgresGenerator;
import com.devsu.accountmicroservice.domain.command.DepositoCommand;
import com.devsu.accountmicroservice.domain.command.MovimientoCommand;
import com.devsu.accountmicroservice.domain.command.RetiroCommand;
import com.devsu.accountmicroservice.domain.exception.AccountNotFoundException;
import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.domain.model.Movimiento;
import com.devsu.accountmicroservice.domain.model.enums.CuentaTipo;
import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;
import com.devsu.accountmicroservice.infrastructure.mapper.AccountMapper;
import com.devsu.accountmicroservice.infrastructure.mapper.MovimientoMapper;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.MovimientoEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.CuentaRepository;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.MovimientoRepository;
import com.devsu.accountmicroservice.infrastructure.rest.dto.ProcessMovimientoRequestDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.ProcessMovimientoResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.CuentaTipoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessMovimientoService implements ProcessMovimientoUseCase {
    private final MovimientoIdPostgresGenerator movimientoIdPostgresGenerator;
    private final CuentaRepository cuentaRepository;
    private final AccountMapper accountMapper;
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public ProcessMovimientoResponseDTO execute(ProcessMovimientoRequestDTO request) {
        checkIfAccountExists(request);

        CuentaEntity cuentaEntity = cuentaRepository.findById(request.cuentaId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Cuenta con ID " + request.cuentaId() + " no encontrada."
                ));
        Cuenta cuenta = accountMapper.toDomain(cuentaEntity);

        String id = movimientoIdPostgresGenerator.generateId();
        MovimientoCommand command =
                switch (request.tipo()) {
                    case DEPOSITO -> new DepositoCommand(id, request.monto());
                    case RETIRO -> new RetiroCommand(id, request.monto());
                };

        Movimiento movimiento = cuenta.addTransaction(command);
        MovimientoEntity entity = movimientoMapper.toEntity(movimiento, cuentaEntity);
        movimientoRepository.save(entity);
        CuentaEntity updatedEntity = accountMapper.toEntity(cuenta);
        cuentaRepository.save(updatedEntity);
        return new ProcessMovimientoResponseDTO(
                movimiento.getId(),
                movimiento.getSaldoInicial(),
                cuenta.getId(),
                CuentaTipoDTO.valueOf(cuenta.getTipo().name()),
                cuenta.getEstado(),
                movimiento.getMonto(),
                movimiento.getSaldoDisponible());
    }

    private void checkIfAccountExists(ProcessMovimientoRequestDTO request) {
        if (accountNotFound(request)) {
            throwAccountNotFoundException(request);
        }
    }

    private static void throwAccountNotFoundException(ProcessMovimientoRequestDTO request) {
        throw new AccountNotFoundException(
                "Cuenta con ID " + request.cuentaId() + " no encontrada."
        );
    }

    private boolean accountNotFound(ProcessMovimientoRequestDTO request) {
        return !cuentaRepository.existsById(request.cuentaId());
    }
}
