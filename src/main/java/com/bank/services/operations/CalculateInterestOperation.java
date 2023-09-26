package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.model.CalculateInterestOperationRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import com.bank.utils.ValidationsUtils;

import java.util.List;

public class CalculateInterestOperation implements BankOperation<CalculateInterestOperationRequest, List<BankAccount>>{

    private BankRepository bankRepository;

    public CalculateInterestOperation(BankRepository bankRepository) {
        ValidationsUtils.throwIfNull(bankRepository, "bankRepository must not be null");

        this.bankRepository = bankRepository;
    }

    @Override
    public List<BankAccount> process(CalculateInterestOperationRequest calculateInterestOperationRequest) throws BankApplicationException {

        List<BankAccount> allSavingsAccounts = this.bankRepository.getAllSavingsAccounts();
        System.out.println("Showing all savings account interest");

        for(BankAccount bankAccount : allSavingsAccounts ) {
            Double interest = bankAccount.calculateInterest();
            System.out.println("####################################");
            System.out.println("Account number: "+ bankAccount.getAccountNumber());
            System.out.println("Account holder name: "+ bankAccount.getAccountHolder());
            System.out.println("Account balance: "+ bankAccount.getBalance());
            System.out.println("Interest : "+ interest);
            System.out.println("####################################");
        }

        return allSavingsAccounts;
    }

    @Override
    public CalculateInterestOperationRequest getInputAndBuildRequest(int input) {
        return new CalculateInterestOperationRequest();
    }

    @Override
    public BankOperationType getBankOperationType() {
        return BankOperationType.CALCULATE_INTEREST;
    }
}
