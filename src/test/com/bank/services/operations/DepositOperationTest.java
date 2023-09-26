package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.implementation.SavingsAccount;
import com.bank.model.DepositOperationRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepositOperationTest {

    @Mock
    private BankRepository mockBankRepository;

    private DepositOperation depositOperationUnderTest;

    @Before
    public void setUp() {
        depositOperationUnderTest = new DepositOperation(mockBankRepository);
    }

    @Test
    public void testProcess() {
        final DepositOperationRequest depositOperationRequest = new DepositOperationRequest(0L, 5.0);
        when(mockBankRepository.getValueAndThrowErrorIfNotFound(0L)).thenReturn(new SavingsAccount(0L, "TEST_ACCOUNT", 50.0));

        final BankAccount result = depositOperationUnderTest.process(depositOperationRequest);

        verify(mockBankRepository).save(eq(0L), any(BankAccount.class));
    }

    @Test
    public void testProcess_BankRepositoryGetValueAndThrowErrorIfNotFoundThrowsBankApplicationException() {
        final DepositOperationRequest depositOperationRequest = new DepositOperationRequest(0L, 0.0);

        assertThrows(BankApplicationException.class, () -> depositOperationUnderTest.process(depositOperationRequest));
    }

    @Test
    public void testGetBankOperationType() {
        assertEquals(BankOperationType.DEPOSIT, depositOperationUnderTest.getBankOperationType());
    }
}
