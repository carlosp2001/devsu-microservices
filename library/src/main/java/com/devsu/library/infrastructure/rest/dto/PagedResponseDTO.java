package com.devsu.library.infrastructure.rest.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PagedResponseDTO<T>(List<T> content, int page, int size, long totalElements, int totalPages) {
    public static <T> PagedResponseDTO<T> from(Page<T> page) {
        return new PagedResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
