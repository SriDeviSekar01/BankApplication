package com.bank;

import com.bank.exception.BankApplicationException;

import static org.junit.Assert.assertThrows;

public class BankApplicationTest {

    //@Test
    public void testMain() {
        BankApplication.main(new String[]{"args"});
    }

    //@Test
    public void testMain_ThrowsBankApplicationException() {
        assertThrows(BankApplicationException.class, () -> BankApplication.main(new String[]{"args"}));
    }
}
