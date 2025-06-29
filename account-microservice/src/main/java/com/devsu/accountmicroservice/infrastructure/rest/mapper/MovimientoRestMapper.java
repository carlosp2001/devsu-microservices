package com.devsu.accountmicroservice.infrastructure.rest.mapper;

import com.devsu.accountmicroservice.domain.model.Movimiento;
import com.devsu.accountmicroservice.domain.model.enums.MovimientoTipo;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.MovimientoTipoDTO;
import org.springframework.stereotype.Component;

@Component
public class MovimientoRestMapper {
    public GetMovimientoResponseDTO toResponseDTO(Movimiento domain) {
        if (domain == null) {
            return null;
        }
        return new GetMovimientoResponseDTO(
                domain.getId(),
                toDTO(domain.getTipo()),
                domain.getCuentaId(),
                domain.getMonto(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public MovimientoTipoDTO toDTO(MovimientoTipo domain) {
        if (domain == null) {
            return null;
        }
        return switch (domain) {
            case RETIRO -> MovimientoTipoDTO.RETIRO;
            case DEPOSITO -> MovimientoTipoDTO.DEPOSITO;
        };
    }
}
