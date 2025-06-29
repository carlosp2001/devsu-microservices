package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.GetAccountByIdUseCase;
import com.devsu.accountmicroservice.domain.exception.AccountNotFoundException;
import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.infrastructure.mapper.AccountMapper;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.CuentaRepository;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetAccountByIdResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.mapper.CuentaRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountByIdService implements GetAccountByIdUseCase {
    private final CuentaRepository cuentaRepository;
    private final AccountMapper accountMapper;
    private final CuentaRestMapper cuentaRestMapper;

    @Override
    public GetAccountByIdResponseDTO execute(String accountId) {
        CuentaEntity cuentaEntity = cuentaRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada con ID: " + accountId));
        Cuenta account = accountMapper.toDomain(cuentaEntity);
        return cuentaRestMapper.toResponseDTO(account);
    }
}
