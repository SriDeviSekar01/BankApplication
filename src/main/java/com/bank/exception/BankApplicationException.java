package com.bank.exception;

public class BankApplicationException extends RuntimeException
{
    public BankApplicationException(String message) {
        super(message);
    }
}
