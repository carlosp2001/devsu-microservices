package com.devsu.accountmicroservice.application.port.in;

import com.devsu.accountmicroservice.infrastructure.rest.dto.GetAccountByIdResponseDTO;

public interface GetAccountByIdUseCase {
    GetAccountByIdResponseDTO execute(String accountId);
}
