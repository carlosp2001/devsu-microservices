package com.devsu.accountmicroservice.infrastructure.mapper;

import com.devsu.accountmicroservice.domain.model.Movimiento;
import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.CuentaEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {
    public MovimientoEntity toEntity(Movimiento domain, CuentaEntity cuentaEntity) {
        if (domain == null) {
            return null;
        }
        MovimientoEntity entity = new MovimientoEntity();
        entity.setId(domain.getId());
        entity.setTipo(domain.getTipo().name());
        entity.setCuentaEntity(cuentaEntity);
        entity.setMonto(domain.getMonto());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setSaldoDisponible(domain.getSaldoDisponible());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public Movimiento toDomain(MovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return Movimiento.hydrate(
                entity.getId(),
                MovimientoTipo.valueOf(entity.getTipo()),
                entity.getCuentaEntity().getId(),
                entity.getMonto(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getSaldoInicial(),
                entity.getSaldoDisponible()
        );
    }
}
