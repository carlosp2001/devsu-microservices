package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.command.UpdateAccountCommand;
import com.devsu.accountmicroservice.application.port.in.UpdateAccountUseCase;
import com.devsu.accountmicroservice.domain.exception.AccountNotFoundException;
import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.infrastructure.mapper.AccountMapper;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAccountService implements UpdateAccountUseCase {
    private final CuentaRepository cuentaRepository;
    private final AccountMapper accountMapper;

    @Override
    public void execute(UpdateAccountCommand request) {
        CuentaEntity entity = cuentaRepository.findById(request.accountId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada con ID: " + request.accountId()));

        Cuenta cuenta = accountMapper.toDomain(entity);
        cuenta.update(request.estado());
        entity = accountMapper.toEntity(cuenta);
        cuentaRepository.save(entity);
    }
}
