package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.DeleteAccountUseCase;
import com.devsu.accountmicroservice.domain.exception.AccountNotFoundException;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountService implements DeleteAccountUseCase {
    private final CuentaRepository cuentaRepository;

    @Override
    public void execute(String accountId) {
        if (!cuentaRepository.existsById(accountId)) {
            throw new AccountNotFoundException("Cuenta no encontrada con ID: " + accountId);
        }
        cuentaRepository.deleteById(accountId);
    }
}
