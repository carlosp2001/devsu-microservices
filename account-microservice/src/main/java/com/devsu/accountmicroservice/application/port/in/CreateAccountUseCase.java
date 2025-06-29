package com.devsu.accountmicroservice.application.port.in;

import com.devsu.accountmicroservice.application.command.CreateAccountCommand;
import com.devsu.library.domain.model.Peticion;

public interface CreateAccountUseCase {
    Peticion execute(CreateAccountCommand command);
}
