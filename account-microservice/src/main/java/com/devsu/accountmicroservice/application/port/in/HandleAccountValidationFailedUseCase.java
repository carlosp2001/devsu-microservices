package com.devsu.accountmicroservice.application.port.in;

import com.devsu.library.domain.model.Peticion;

public interface HandleAccountValidationFailedUseCase {
    void execute(Peticion request);
}
