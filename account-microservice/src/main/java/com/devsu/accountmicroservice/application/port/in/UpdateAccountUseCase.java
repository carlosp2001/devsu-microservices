package com.devsu.accountmicroservice.application.port.in;

import com.devsu.accountmicroservice.application.command.UpdateAccountCommand;

public interface UpdateAccountUseCase {
    void execute(UpdateAccountCommand command);
}
