package com.bank.utils;

import org.junit.Test;

import java.util.function.Predicate;

public class ValidationsUtilsTest {

    @Test
    public void testThrowIfNull() {
        ValidationsUtils.throwIfNull("obj", "errorMessage");

    }

    @Test
    public void testThrowIfConditionMet() {
        final Predicate<String> condition = val -> {
            return false;
        };

        ValidationsUtils.throwIfConditionMet(condition, "value", "errorMessage");

    }
}
