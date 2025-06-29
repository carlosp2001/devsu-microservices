package com.devsu.accountmicroservice.infrastructure.rest.mapper;

import com.devsu.accountmicroservice.domain.model.Cuenta;
import com.devsu.accountmicroservice.domain.model.enums.CuentaTipo;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetAccountByIdResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.CuentaTipoDTO;
import org.springframework.stereotype.Component;

@Component
public class CuentaRestMapper {
    public GetAccountByIdResponseDTO toResponseDTO(Cuenta domain) {
        return new GetAccountByIdResponseDTO(
                domain.getId(),
                domain.getSaldo(),
                toCuentaTipoDTO(domain.getTipo()),
                domain.getEstado(),
                domain.getClienteId(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public CuentaTipoDTO toCuentaTipoDTO(CuentaTipo domain) {
        if (domain == null) {
            return null;
        }
        return switch (domain) {
            case AHORRO -> CuentaTipoDTO.AHORRO;
            case CORRIENTE -> CuentaTipoDTO.CORRIENTE;
        };
    }
}
