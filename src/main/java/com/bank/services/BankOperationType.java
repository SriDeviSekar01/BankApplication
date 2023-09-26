package com.bank.services;

public enum BankOperationType {
    CREATE_ACCOUNT("Account created successfully"),
    WITHDRAW("Amount withdrawn successfully"),
    DEPOSIT("Amount deposited successfully"),
    BALANCE_INQUIRY("Available balance is"),
    CALCULATE_INTEREST("Interest calculated successfully"),
    QUIT("Thank you!. Visit again...");

    private final String postProcessLogMessage;

    BankOperationType(String postProcessLogMessage) {
        this.postProcessLogMessage = postProcessLogMessage;
    }

    public String getPostProcessLogMessage() {
        return postProcessLogMessage;
    }
}
