package com.bank.services;

import com.bank.services.operations.AccountType;

public abstract class BankAccount {
    private Long accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(Long accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double updatedBalance) {
        this.balance = updatedBalance;
    }

    public abstract BankAccount deposit(double amount);
    public abstract BankAccount withdraw(double amount);
    public abstract double calculateInterest();

    public abstract AccountType accountType();

    @Override
    public String toString() {
        return "BankAccount Details : " +
                "accountNumber=" + accountNumber +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance;
    }
}
