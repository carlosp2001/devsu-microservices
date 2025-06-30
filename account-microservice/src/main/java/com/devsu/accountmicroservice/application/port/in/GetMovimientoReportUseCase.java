package com.devsu.accountmicroservice.application.port.in;

import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoReportResponseDTO;
import com.devsu.library.infrastructure.rest.dto.PagedResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface GetMovimientoReportUseCase {
    PagedResponseDTO<GetMovimientoReportResponseDTO> generateReport(
            LocalDateTime startDate,
            LocalDateTime endDate,
            String clientId,
            Pageable pageable
    );
}
