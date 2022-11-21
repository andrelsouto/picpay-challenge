package com.soultotec.transactionservice.adapters.inbound.web.controllers;

import com.soultotec.transactionservice.application.core.impl.NotFoundExpection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandlerController {

    @ExceptionHandler(NotFoundExpection.class)
    protected ResponseEntity<Map<String, String>> handlePageNotFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
