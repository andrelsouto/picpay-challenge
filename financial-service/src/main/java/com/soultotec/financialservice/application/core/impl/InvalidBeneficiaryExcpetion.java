package com.soultotec.financialservice.application.core.impl;

public class InvalidBeneficiaryExcpetion extends RuntimeException {
    public InvalidBeneficiaryExcpetion(String meessage) {
        super(meessage);
    }
}
