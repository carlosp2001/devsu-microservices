package com.devsu.accountmicroservice.infrastructure.rest.controller;

import com.devsu.accountmicroservice.application.port.in.GetMovimientoReportUseCase;
import com.devsu.accountmicroservice.application.service.GetMovimientoReportService;
import com.devsu.accountmicroservice.infrastructure.rest.dto.GetMovimientoReportResponseDTO;
import com.devsu.library.infrastructure.rest.dto.PagedResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
public class ReportController {
    private final GetMovimientoReportUseCase getMovimientoReportUseCase;

    @GetMapping
    public ResponseEntity<PagedResponseDTO<GetMovimientoReportResponseDTO>> generateReport
            (@RequestParam("fechaInicio") ZonedDateTime startDate,
             @RequestParam("fechaFinal") ZonedDateTime endDate,
             @RequestParam("clientId") String clientId,
             Pageable pageable) {
        LocalDateTime startDateUtc = startDate.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime endDateUtc = endDate.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        return ResponseEntity.ok(getMovimientoReportUseCase.generateReport(startDateUtc, endDateUtc, clientId, pageable));
    }
}
