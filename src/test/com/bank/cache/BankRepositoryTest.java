package com.bank.cache;

import com.bank.exception.BankApplicationException;
import com.bank.implementation.CheckingAccount;
import com.bank.implementation.SavingsAccount;
import com.bank.services.BankAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankRepositoryTest {

    @Mock
    private ConcurrentHashMap<Long, BankAccount> mockCache;

    @InjectMocks
    private BankRepository bankRepositoryUnderTest;

    @Test
    public void testHasKey() {
        when(mockCache.containsKey(0L)).thenReturn(false);

        final boolean result = bankRepositoryUnderTest.hasKey(0L);

        assertFalse(result);
    }

    @Test
    public void testHasKey_ConcurrentHashMapReturnsTrue() {
        when(mockCache.containsKey(0L)).thenReturn(true);

        final boolean result = bankRepositoryUnderTest.hasKey(0L);

        assertTrue(result);
    }

    @Test
    public void testGetValueAndThrowErrorIfNotFound() {
        when(mockCache.get(0L)).thenReturn(new CheckingAccount(23l, "sri", 15.0));

        final BankAccount result = bankRepositoryUnderTest.getValueAndThrowErrorIfNotFound(0L);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getAccountNumber().toString(), "23");
        Assert.assertEquals(result.getAccountHolder(), "sri");
        Assert.assertEquals(result.getBalance(), 15.0, 0.0);

    }

    @Test
    public void testGetValueAndThrowErrorIfNotFound_ConcurrentHashMapReturnsNull() {
        when(mockCache.get(0L)).thenReturn(null);

        assertThrows(BankApplicationException.class, () -> bankRepositoryUnderTest.getValueAndThrowErrorIfNotFound(0L));
    }

    @Test
    public void testGetAllSavingsAccounts() {
        // Configure ConcurrentHashMap.entrySet(...).
        final Set<Map.Entry<Long, BankAccount>> entries = new HashSet<>(
                Arrays.asList(new AbstractMap.SimpleEntry<>(23L, new SavingsAccount(23l, "sri", 0.0))));

        when(mockCache.entrySet()).thenReturn(entries);

        final List<BankAccount> result = bankRepositoryUnderTest.getAllSavingsAccounts();

        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getAccountNumber().toString(), "23");
        Assert.assertEquals(result.get(0).getAccountHolder(), "sri");
        Assert.assertEquals(result.get(0).getBalance(), 0.0, 0.0);

    }

    @Test
    public void testGetAllSavingsAccounts_ConcurrentHashMapReturnsNoItems() {
        when(mockCache.entrySet()).thenReturn(Collections.emptySet());

        final List<BankAccount> result = bankRepositoryUnderTest.getAllSavingsAccounts();

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testSave() {
        final BankAccount bankAccount = new CheckingAccount(1l,"sri",0.0);

        final BankAccount result = bankRepositoryUnderTest.save(0L, bankAccount);

        verify(mockCache).put(eq(0L), any(BankAccount.class));
    }
}
