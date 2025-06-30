package com.devsu.accountmicroservice.application.service;

import com.devsu.accountmicroservice.application.port.in.GetMovimientoReportUseCase;
import com.devsu.accountmicroservice.infrastructure.persistence.entity.MovimientoEntity;
import com.devsu.accountmicroservice.infrastructure.persistence.repository.MovimientoRepository;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoReportResponseDTO;
import com.devsu.accountmicroservice.infrastructure.rest.dto.enums.CuentaTipoDTO;
import com.devsu.library.infrastructure.rest.dto.PagedResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GetMovimientoReportService implements GetMovimientoReportUseCase {
    private final MovimientoRepository movimientoRepository;

    @Override
    public PagedResponseDTO<GetMovimientoReportResponseDTO> generateReport(LocalDateTime startDate, LocalDateTime endDate, String clientId, Pageable pageable) {
        Page<MovimientoEntity> movimientoEntities = movimientoRepository.findAllByClientAndDateBetween(clientId, startDate, endDate, pageable);
        Page<GetMovimientoReportResponseDTO> dtoPage = movimientoEntities.map(entity ->
                new GetMovimientoReportResponseDTO(
                entity.getCreatedAt(),
                entity.getCuentaEntity().getClienteId(),
                entity.getCuentaEntity().getId(),
                CuentaTipoDTO.valueOf(entity.getCuentaEntity().getTipo()),
                entity.getSaldoInicial(),
                entity.getCuentaEntity().getEstado(),
                entity.getMonto(),
                entity.getSaldoDisponible()
        ));
        return PagedResponseDTO.from(dtoPage);
    }
}
