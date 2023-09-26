package com.bank.services.operations;

import com.bank.exception.BankApplicationException;
import com.bank.services.BankOperationType;

public interface BankOperation<REQUEST, RESPONSE> {
    RESPONSE process(REQUEST request) throws BankApplicationException;
    REQUEST getInputAndBuildRequest(int input);
    BankOperationType getBankOperationType();
}
