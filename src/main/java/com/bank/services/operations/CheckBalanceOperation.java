package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.model.CheckBalanceOperationRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import com.bank.utils.ScannerUtils;
import com.bank.utils.ValidationsUtils;

import java.util.Scanner;

public class CheckBalanceOperation implements BankOperation<CheckBalanceOperationRequest, Double>{

    private BankRepository bankRepository;

    public CheckBalanceOperation(BankRepository bankRepository) {
        ValidationsUtils.throwIfNull(bankRepository, "bankRepository must not be null");

        this.bankRepository = bankRepository;
    }

    @Override
    public Double process(CheckBalanceOperationRequest checkBalanceOperationRequest) throws BankApplicationException {
        BankAccount bankAccount = this.bankRepository.getValueAndThrowErrorIfNotFound(checkBalanceOperationRequest.getAccountNumber());

        return bankAccount.getBalance();
    }

    @Override
    public CheckBalanceOperationRequest getInputAndBuildRequest(int input) {
        Scanner scanner = ScannerUtils.getScannerInstance();

        System.out.println("Enter account number");
        long accountNumber = scanner.nextLong();
        return new CheckBalanceOperationRequest(accountNumber);
    }

    @Override
    public BankOperationType getBankOperationType() {
        return BankOperationType.BALANCE_INQUIRY;
    }
}
