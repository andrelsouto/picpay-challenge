package com.soultotec.financialservice.application.core.impl;

public class InvalidBalanceException extends RuntimeException {
    public InvalidBalanceException(String message) {
        super(message);
    }
}
