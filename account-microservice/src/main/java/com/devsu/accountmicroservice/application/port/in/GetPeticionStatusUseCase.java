package com.devsu.accountmicroservice.application.port.in;

import com.devsu.library.domain.model.Peticion;

import java.util.UUID;

public interface GetPeticionStatusUseCase {
    Peticion execute(UUID peticionId);
}
