package com.devsu.library.infrastructure.rest.exception;

import java.time.Instant;

public record ErrorResponse(String error, String message, Instant timestamp, int status) {
}
