package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.exception.BankApplicationException;
import com.bank.implementation.CheckingAccount;
import com.bank.model.CreateBankAccountRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountOperationTest {

    @Mock
    private BankRepository mockBankRepository;

    private CreateAccountOperation createAccountOperationUnderTest;

    @Before
    public void setUp() {
        createAccountOperationUnderTest = new CreateAccountOperation(mockBankRepository);
    }

    @Test
    public void testProcess() {
        final CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest(1L, "sri", 10.0,
                AccountType.CHECKING);
        when(mockBankRepository.hasKey(1L)).thenReturn(false);
        when(mockBankRepository.save(eq(1L), any(BankAccount.class))).thenReturn(new CheckingAccount(1l,"sri",10.0));

        final BankAccount result = createAccountOperationUnderTest.process(createBankAccountRequest);

        Assert.assertEquals(result.getAccountNumber().toString(),"1");
        Assert.assertEquals(result.getAccountHolder(),"sri");
        Assert.assertEquals(result.getBalance(),10.0,0.0);
    }

    @Test(expected = BankApplicationException.class)
    public void testProcess_BankRepositoryHasKeyReturnsTrue() {
        final CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest(1L, "sri", 10.0,
                AccountType.CHECKING);
        when(mockBankRepository.hasKey(1L)).thenReturn(true);

        createAccountOperationUnderTest.process(createBankAccountRequest);
    }

    @Test
    public void testGetBankOperationType() {
        assertEquals(BankOperationType.CREATE_ACCOUNT, createAccountOperationUnderTest.getBankOperationType());
    }
}
