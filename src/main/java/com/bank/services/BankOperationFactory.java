package com.bank.services;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.services.operations.*;
import com.bank.utils.ValidationsUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BankOperationFactory {
    private Map<BankOperationType, BankOperation> bankOperationMap;
    private BankRepository bankRepository;

    public BankOperationFactory(BankRepository bankRepository) {
        ValidationsUtils.throwIfNull(bankRepository, "bankRepository must not be null");

        this.bankOperationMap = new HashMap<>();
        this.bankRepository = bankRepository;

        this.initializeMap();
    }

    private void initializeMap() {
        CalculateInterestOperation calculateInterestOperation = new CalculateInterestOperation(this.bankRepository);
        CheckBalanceOperation checkBalanceOperation = new CheckBalanceOperation(this.bankRepository);
        CreateAccountOperation createAccountOperation = new CreateAccountOperation(this.bankRepository);
        DepositOperation depositOperation = new DepositOperation(this.bankRepository);
        WithDrawOperation withDrawOperation = new WithDrawOperation(this.bankRepository);

        this.bankOperationMap.put(calculateInterestOperation.getBankOperationType(), calculateInterestOperation);
        this.bankOperationMap.put(checkBalanceOperation.getBankOperationType(), checkBalanceOperation);
        this.bankOperationMap.put(createAccountOperation.getBankOperationType(), createAccountOperation);
        this.bankOperationMap.put(depositOperation.getBankOperationType(), depositOperation);
        this.bankOperationMap.put(withDrawOperation.getBankOperationType(), withDrawOperation);
    }

    public BankOperation getOperationAndThrowErrorIfNotFound(BankOperationType bankOperationType) {
        BankOperation bankOperation = this.bankOperationMap.get(bankOperationType);

        if(Objects.isNull(bankOperation)) {
            throw new BankApplicationException("Operation with type :"+ bankOperationType + " is not supported");
        }

        return bankOperation;
    }
}
