package com.bank.model;

public class WithDrawOperationRequest {
    private Long accountNumber;
    private double amount;

    public WithDrawOperationRequest(Long accountNumber, double amount) {

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
