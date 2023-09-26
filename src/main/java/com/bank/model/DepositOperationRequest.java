package com.bank.model;

import com.bank.utils.ValidationsUtils;

public class DepositOperationRequest {
    private Long accountNumber;
    private double amount;

    public DepositOperationRequest(Long accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
