package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.model.WithDrawOperationRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import com.bank.utils.ScannerUtils;
import com.bank.utils.ValidationsUtils;

import java.util.Scanner;

public class WithDrawOperation implements BankOperation<WithDrawOperationRequest, BankAccount>{

    private BankRepository bankRepository;

    public WithDrawOperation(BankRepository bankRepository) {
        ValidationsUtils.throwIfNull(bankRepository, "bankRepository must not be null");

        this.bankRepository = bankRepository;
    }

    @Override
    public BankAccount process(WithDrawOperationRequest withDrawOperationRequest) throws BankApplicationException {
        BankAccount bankAccount = this.bankRepository.getValueAndThrowErrorIfNotFound(withDrawOperationRequest.getAccountNumber());
        this.validate(bankAccount, withDrawOperationRequest);

        BankAccount updatedBankAccount = bankAccount.withdraw(withDrawOperationRequest.getAmount());
        this.bankRepository.save(withDrawOperationRequest.getAccountNumber(), updatedBankAccount);

        return updatedBankAccount;
    }

    @Override
    public WithDrawOperationRequest getInputAndBuildRequest(int input) {
        Scanner scanner = ScannerUtils.getScannerInstance();

        System.out.println("Enter account number");
        long accountNumber = scanner.nextLong();
        System.out.println("Enter amount to withdraw");
        double amount = scanner.nextDouble();
        return new WithDrawOperationRequest(accountNumber, amount);
    }

    @Override
    public BankOperationType getBankOperationType() {
        return BankOperationType.WITHDRAW;
    }

    private void validate(BankAccount bankAccount, WithDrawOperationRequest withDrawOperationRequest) throws BankApplicationException {

        if(withDrawOperationRequest.getAmount() <= 0 ) {
            throw new BankApplicationException("Withdrawal amount cannot be less than or equals to 0");
        }

        if( (bankAccount.getBalance() - withDrawOperationRequest.getAmount()) < 0 ) {
            throw new BankApplicationException("Amount insufficient to withdraw");
        }}
}
