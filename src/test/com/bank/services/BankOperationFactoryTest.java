package com.bank.services;

import com.bank.cache.BankRepository;
import com.bank.services.operations.BankOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankOperationFactoryTest {

    @Mock
    private BankRepository mockBankRepository;

    private BankOperationFactory bankOperationFactoryUnderTest;

    @Before
    public void setUp() {
        bankOperationFactoryUnderTest = new BankOperationFactory(mockBankRepository);
    }

    @Test
    public void testGetOperationAndThrowErrorIfNotFound() {
        final BankOperation result = bankOperationFactoryUnderTest.getOperationAndThrowErrorIfNotFound(
                BankOperationType.CREATE_ACCOUNT);

    }
}
