package com.bank.model;

import com.bank.services.operations.AccountType;
import com.bank.utils.ValidationsUtils;

public class CreateBankAccountRequest {
    private Long accountNumber;
    private String accountHolderName;
    private double initialBalance;
    private AccountType bankAccountType;

    public CreateBankAccountRequest(Long accountNumber, String accountHolderName, double initialBalance, AccountType bankAccountType) {
        ValidationsUtils.throwIfNull(accountNumber, "accountNumber must not be null");
        ValidationsUtils.throwIfNull(accountHolderName, "accountHolderName must not be null");
        ValidationsUtils.throwIfNull(initialBalance, "initialBalance must not be null");
        ValidationsUtils.throwIfNull(bankAccountType, "accountType must not be null");
        ValidationsUtils.throwIfConditionMet(ValidationsUtils.valueLessThanOrEqualsZero, accountNumber, "accountNumber must not be lesser than zero");
        ValidationsUtils.throwIfConditionMet(ValidationsUtils.valueLessThanZero, initialBalance,"accountNumber must not be lesser than zero");

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.initialBalance = initialBalance;
        this.bankAccountType = bankAccountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public AccountType getAccountType() {
        return bankAccountType;
    }
}
