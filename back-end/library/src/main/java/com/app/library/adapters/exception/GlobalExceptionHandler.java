package com.app.library.adapters.exception;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.exception.ForbiddenException;
import com.app.library.domain.entity.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException exception, WebRequest request) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleUseCaseException(ApplicationException exception, WebRequest request) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException exception, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUnknownRuntime(RuntimeException exception, WebRequest request) {
        return ResponseEntity
            .internalServerError()
            .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownError(Exception exception, WebRequest request) {
        return ResponseEntity
            .internalServerError()
            .body(Map.of("error", exception.getMessage()));
    }
}
