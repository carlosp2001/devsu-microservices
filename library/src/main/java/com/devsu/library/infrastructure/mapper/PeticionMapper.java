package com.devsu.library.infrastructure.mapper;

import com.devsu.library.domain.model.Peticion;
import com.devsu.library.infrastructure.persistence.entity.PeticionEntity;

public class PeticionMapper {
    public PeticionEntity toEntity(Peticion peticion) {
        PeticionEntity peticionEntity = new PeticionEntity();
        peticionEntity.setPeticionId(peticion.getPeticionId());
        peticionEntity.setMensaje(peticion.getMensaje());
        peticionEntity.setEstado(peticion.getEstado());
        peticionEntity.setCreatedAt(peticion.getCreatedAt());
        peticionEntity.setUpdatedAt(peticion.getUpdatedAt());
        return peticionEntity;
    }
}
