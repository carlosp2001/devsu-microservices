package com.devsu.accountmicroservice.infrastructure.mapper;


import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public CuentaEntity toEntity(Cuenta domain) {
        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setTipo(domain.getTipo().name());
        entity.setEstado(domain.getEstado());
        entity.setClienteId(domain.getClienteId());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
}
