package com.bank.services.operations;

import com.bank.cache.BankRepository;
import com.bank.implementation.SavingsAccount;
import com.bank.model.CalculateInterestOperationRequest;
import com.bank.services.BankAccount;
import com.bank.services.BankOperationType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculateInterestOperationTest {

    @Mock
    private BankRepository mockBankRepository;

    private CalculateInterestOperation calculateInterestOperationUnderTest;

    @Before
    public void setUp() {
        calculateInterestOperationUnderTest = new CalculateInterestOperation(mockBankRepository);
    }

    @Test
    public void testProcess() {
        final CalculateInterestOperationRequest calculateInterestOperationRequest = new CalculateInterestOperationRequest();
        when(mockBankRepository.getAllSavingsAccounts()).thenReturn(Arrays.asList(new SavingsAccount(1l,"sri",10.0)));

        final List<BankAccount> result = calculateInterestOperationUnderTest.process(calculateInterestOperationRequest);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(),1);
        Assert.assertEquals(result.get(0).getAccountNumber().toString(),"1");
        Assert.assertEquals(result.get(0).getAccountHolder(),"sri");
        Assert.assertEquals(result.get(0).getBalance(),10.0,0.0);
        Assert.assertEquals(result.get(0).calculateInterest(),0.3,0.0);
    }

    @Test
    public void testProcess_BankRepositoryReturnsNoItems() {
        final CalculateInterestOperationRequest calculateInterestOperationRequest = new CalculateInterestOperationRequest();
        when(mockBankRepository.getAllSavingsAccounts()).thenReturn(Collections.emptyList());

        final List<BankAccount> result = calculateInterestOperationUnderTest.process(calculateInterestOperationRequest);

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetInputAndBuildRequest() {
        final CalculateInterestOperationRequest result = calculateInterestOperationUnderTest.getInputAndBuildRequest(0);

        Assert.assertNotNull(result);
    }

    @Test
    public void testGetBankOperationType() {
        assertEquals(BankOperationType.CALCULATE_INTEREST, calculateInterestOperationUnderTest.getBankOperationType());
    }
}
