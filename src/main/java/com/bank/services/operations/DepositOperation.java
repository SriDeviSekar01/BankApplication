package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.model.DepositOperationRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import com.bank.utils.ScannerUtils;
import com.bank.utils.ValidationsUtils;

import java.util.Scanner;

public class DepositOperation implements BankOperation<DepositOperationRequest, BankAccount>{

    private BankRepository bankRepository;

    public DepositOperation(BankRepository bankRepository) {
        ValidationsUtils.throwIfNull(bankRepository, "bankRepository must not be null");

        this.bankRepository = bankRepository;
    }

    @Override
    public BankAccount process(DepositOperationRequest depositOperationRequest) throws BankApplicationException {
        this.validate(depositOperationRequest);

        BankAccount bankAccount = this.bankRepository.getValueAndThrowErrorIfNotFound(depositOperationRequest.getAccountNumber());
        BankAccount updatedBankAccount = bankAccount.deposit(depositOperationRequest.getAmount());
        this.bankRepository.save(depositOperationRequest.getAccountNumber(), updatedBankAccount);

        return updatedBankAccount;
    }

    private void validate(DepositOperationRequest depositOperationRequest) {
        if(depositOperationRequest.getAmount() <= 0 ) {
            throw new BankApplicationException("Deposit amount cannot be less than or equals to 0");
        }
    }

    @Override
    public DepositOperationRequest getInputAndBuildRequest(int input) {
        Scanner scanner = ScannerUtils.getScannerInstance();

        System.out.println("Enter account number");
        long accountNumber = scanner.nextLong();
        System.out.println("Enter amount to deposit");
        double amount = scanner.nextDouble();
        return new DepositOperationRequest(accountNumber, amount);
    }

    @Override
    public BankOperationType getBankOperationType() {
        return BankOperationType.DEPOSIT;
    }
}
