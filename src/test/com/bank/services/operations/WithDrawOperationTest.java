package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.implementation.SavingsAccount;
import com.bank.model.WithDrawOperationRequest;
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
public class WithDrawOperationTest {

    @Mock
    private BankRepository mockBankRepository;

    private WithDrawOperation withDrawOperationUnderTest;

    @Before
    public void setUp() {
        withDrawOperationUnderTest = new WithDrawOperation(mockBankRepository);
    }

    @Test
    public void testProcess() {
        final WithDrawOperationRequest withDrawOperationRequest = new WithDrawOperationRequest(0L, 10.0);
        when(mockBankRepository.getValueAndThrowErrorIfNotFound(0L)).thenReturn(new SavingsAccount(0L, "TEST_ACCOUNT", 50.0));

        final BankAccount result = withDrawOperationUnderTest.process(withDrawOperationRequest);

        verify(mockBankRepository).save(eq(0L), any(BankAccount.class));
    }

    @Test
    public void testProcess_BankRepositoryGetValueAndThrowErrorIfNotFoundThrowsBankApplicationException() {
        final WithDrawOperationRequest withDrawOperationRequest = new WithDrawOperationRequest(0L, 0.0);
        when(mockBankRepository.getValueAndThrowErrorIfNotFound(0L)).thenThrow(BankApplicationException.class);

        assertThrows(BankApplicationException.class,
                () -> withDrawOperationUnderTest.process(withDrawOperationRequest));
    }

    @Test
    public void testGetBankOperationType() {
        assertEquals(BankOperationType.WITHDRAW, withDrawOperationUnderTest.getBankOperationType());
    }
}
