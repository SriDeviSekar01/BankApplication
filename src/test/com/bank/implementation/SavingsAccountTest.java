package com.bank.implementation;

import com.bank.services.BankAccount;
import com.bank.services.operations.AccountType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SavingsAccountTest {

    private SavingsAccount savingsAccountUnderTest;

    @Before
    public void setUp() {
        savingsAccountUnderTest = new SavingsAccount(0L, "accountHolder", 10.0);
    }

    @Test
    public void testDeposit() {
        final BankAccount result = savingsAccountUnderTest.deposit(1.0);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBalance(),11.0,0.0);
    }

    @Test
    public void testWithdraw() {
        final BankAccount result = savingsAccountUnderTest.withdraw(1.0);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBalance(),9.0,0.0);
    }

    @Test
    public void testCalculateInterest() {
        assertEquals(0.3, savingsAccountUnderTest.calculateInterest(), 0.0);
    }

    @Test
    public void testAccountType() {
        assertEquals(AccountType.SAVINGS, savingsAccountUnderTest.accountType());
    }
}
