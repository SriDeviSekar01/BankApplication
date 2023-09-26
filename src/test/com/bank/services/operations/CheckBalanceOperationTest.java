package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.implementation.CheckingAccount;
import com.bank.model.CheckBalanceOperationRequest;
import com.bank.services.BankOperationType;
import com.bank.utils.ScannerUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckBalanceOperationTest {

    @Mock
    private BankRepository mockBankRepository;
    private CheckBalanceOperation checkBalanceOperationUnderTest;

    @Before
    public void setUp() {
        checkBalanceOperationUnderTest = new CheckBalanceOperation(mockBankRepository);
    }

    @Test
    public void testProcess() {
        final CheckBalanceOperationRequest checkBalanceOperationRequest = new CheckBalanceOperationRequest(0L);
        when(mockBankRepository.getValueAndThrowErrorIfNotFound(0L)).thenReturn(new CheckingAccount(1l,"sri",10.0));

        final Double result = checkBalanceOperationUnderTest.process(checkBalanceOperationRequest);

        assertEquals(10.0, result, 0.0);
    }

    @Test
    public void testProcess_BankRepositoryThrowsBankApplicationException() {
        final CheckBalanceOperationRequest checkBalanceOperationRequest = new CheckBalanceOperationRequest(0L);
        when(mockBankRepository.getValueAndThrowErrorIfNotFound(0L)).thenThrow(BankApplicationException.class);

        assertThrows(BankApplicationException.class,
                () -> checkBalanceOperationUnderTest.process(checkBalanceOperationRequest));
    }

    @Test
    public void testGetBankOperationType() {
        assertEquals(BankOperationType.BALANCE_INQUIRY, checkBalanceOperationUnderTest.getBankOperationType());
    }
}
