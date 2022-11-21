package com.soultotec.financialservice.adapters.inbound.web.controllers;

import com.soultotec.financialservice.application.core.impl.InvalidBalanceException;
import com.soultotec.financialservice.application.core.impl.PageNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status).body(this.getErrors(ex));
    }

    @ExceptionHandler(InvalidBalanceException.class)
    protected ResponseEntity<Map<String, String>> handleInvalidBalanceException(InvalidBalanceException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("balance", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(PageNotFoundException.class)
    protected ResponseEntity<Map<String, String>> handlePageNotFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private Map<String, String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }
}
