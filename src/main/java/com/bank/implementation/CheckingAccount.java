package com.bank.implementation;

import com.bank.services.BankAccount;
import com.bank.services.operations.AccountType;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(Long accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public BankAccount deposit(double amount) {
        double updatedBalance = Double.sum(this.getBalance(), amount);
        super.updateBalance(updatedBalance);

        return this;
    }

    @Override
    public BankAccount withdraw(double amount) {
        double updatedBalance = this.getBalance() - amount;
        super.updateBalance(updatedBalance);
        return this;
    }

    @Override
    public double calculateInterest() {
        throw new UnsupportedOperationException("Calculate interest operation not supported for checking account");
    }

    @Override
    public AccountType accountType() {
        return AccountType.CHECKING;
    }
}
