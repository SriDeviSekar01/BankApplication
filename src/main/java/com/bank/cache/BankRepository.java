package com.bank.cache;

import com.bank.exception.BankApplicationException;
import com.bank.services.BankAccount;
import com.bank.services.operations.AccountType;

import java.util.List;
import java.util.Objects;e
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BankRepository {

    private ConcurrentHashMap<Long, BankAccount> cache;
    public BankRepository() {
        this.cache = new ConcurrentHashMap<>();
    }

    public boolean hasKey(Long key) {
        return this.cache.containsKey(key);
    }

    public BankAccount getValueAndThrowErrorIfNotFound(Long key) throws BankApplicationException {
        BankAccount bankAccount = this.cache.get(key);

        if(Objects.isNull(bankAccount)) {
            throw new BankApplicationException("Account not found for number : "+ key.toString());
        }

        return bankAccount;
    }

    public List<BankAccount> getAllSavingsAccounts() {
        List<BankAccount> savingsBankAccounts = this.cache.entrySet().stream()
                .filter(accountNumberBankAccountEntry -> accountNumberBankAccountEntry.getValue().accountType() == AccountType.SAVINGS)
                .map(accountNumberBankAccountEntry -> accountNumberBankAccountEntry.getValue() )
                .collect(Collectors.toList());

        return savingsBankAccounts;
    }
    public BankAccount save(Long key, BankAccount bankAccount) {
        this.cache.put(key, bankAccount);
        return bankAccount;
    }

    @Override
    public String toString() {
        return "BankCache{" +
                "cache=" + cache +
                '}';
    }
}
