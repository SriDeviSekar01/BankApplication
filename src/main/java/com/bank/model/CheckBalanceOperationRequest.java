package com.bank.model;

public class CheckBalanceOperationRequest {
    private Long accountNumber;
    public CheckBalanceOperationRequest(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }
}
