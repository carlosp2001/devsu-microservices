package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.GetPeticionStatusUseCase;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.PeticionRepository;
import com.devsu.library.domain.exception.PeticionNotFoundException;
import com.devsu.library.domain.model.Peticion;
import com.devsu.library.infrastructure.mapper.PeticionMapper;
import com.devsu.library.infrastructure.persistence.entity.PeticionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPeticionStatusService implements GetPeticionStatusUseCase {
    private final PeticionRepository peticionRepository;
    private final PeticionMapper peticionMapper;

    @Override
    public Peticion execute(UUID peticionId) {
        PeticionEntity peticionEntity = peticionRepository.findById(peticionId).orElseThrow(() -> {
            throw new PeticionNotFoundException("Petición no encontrada con ID: " + peticionId);
        });
        return peticionMapper.toDomain(peticionEntity);
    }
}
