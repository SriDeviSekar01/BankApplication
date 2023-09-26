package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.implementation.CheckingAccount;
import com.bank.implementation.SavingsAccount;
import com.bank.model.CreateBankAccountRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import com.bank.utils.ScannerUtils;
import com.bank.utils.ValidationsUtils;

import java.util.Scanner;

public class CreateAccountOperation implements BankOperation<CreateBankAccountRequest, BankAccount>
{
    private BankRepository bankRepository;

    public CreateAccountOperation(BankRepository bankRepository) {
        ValidationsUtils.throwIfNull(bankRepository, "bankRepository must not be null");

        this.bankRepository = bankRepository;
    }

    @Override
    public BankAccount process(CreateBankAccountRequest createBankAccountRequest) throws BankApplicationException {

        boolean isAccountWithSameAccountNumberExists = this.bankRepository.hasKey(createBankAccountRequest.getAccountNumber());

        if(isAccountWithSameAccountNumberExists) {
            throw new BankApplicationException("Account number already exists");
        }

        BankAccount bankAccount = this.buildBankAccountBasedOnAccountType(createBankAccountRequest);
        BankAccount savedBankAccount = this.bankRepository.save(createBankAccountRequest.getAccountNumber(), bankAccount);

        return savedBankAccount;
    }


    @Override
    public CreateBankAccountRequest getInputAndBuildRequest(int input) {
        Scanner scanner = ScannerUtils.getScannerInstance();

        System.out.println("Enter account number");
        Long accountNumber = scanner.nextLong();
        System.out.println("Enter account holder's name");

        String accountHolderName = scanner.next();
        System.out.println("Enter initial balance");

        double initialBalance = scanner.nextDouble();

        return new CreateBankAccountRequest(accountNumber, accountHolderName, initialBalance, input == 1 ? AccountType.CHECKING : AccountType.SAVINGS);
    }

    @Override
    public BankOperationType getBankOperationType() {
        return BankOperationType.CREATE_ACCOUNT;
    }

    private BankAccount buildBankAccountBasedOnAccountType(CreateBankAccountRequest createBankAccountRequest) {
        BankAccount bankAccount = createBankAccountRequest.getAccountType() == AccountType.CHECKING ?
            new CheckingAccount(createBankAccountRequest.getAccountNumber(), createBankAccountRequest.getAccountHolderName(), createBankAccountRequest.getInitialBalance())
            : new SavingsAccount(createBankAccountRequest.getAccountNumber(), createBankAccountRequest.getAccountHolderName(), createBankAccountRequest.getInitialBalance());

        return bankAccount;
    }
}
