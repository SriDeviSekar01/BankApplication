package com.bank.implementation;

import com.bank.services.BankAccount;
import com.bank.services.operations.AccountType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckingAccountTest {

    private CheckingAccount checkingAccountUnderTest;

    @Before
    public void setUp() {
        checkingAccountUnderTest = new CheckingAccount(0L, "accountHolder", 10.0);
    }

    @Test
    public void testDeposit() {
        final BankAccount result = checkingAccountUnderTest.deposit(1.0);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBalance(),11.0,0.0);
    }

    @Test
    public void testWithdraw() {
        final BankAccount result = checkingAccountUnderTest.withdraw(1.0);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBalance(),9.0,0.0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCalculateInterest() {
        checkingAccountUnderTest.calculateInterest();
    }

    @Test
    public void testAccountType() {
        assertEquals(AccountType.CHECKING, checkingAccountUnderTest.accountType());
    }
}
