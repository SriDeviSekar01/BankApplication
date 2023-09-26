package com.bank.utils;

import com.bank.exception.BankApplicationException;

import java.util.Objects;
import java.util.function.Predicate;

public class ValidationsUtils {

    public static final Predicate<Long> valueLessThanOrEqualsZero = value -> value <= 0;
    public static final Predicate<Double> valueLessThanZero = value -> value < 0;
    public static final Predicate<Double> valueLessThanEqualsZero = value -> value <= 0;

    public static void throwIfNull(Object obj,String errorMessage) {
        if(Objects.isNull(obj)){
            throw new BankApplicationException(errorMessage);
        }
    }

    public static <TYPE> void  throwIfConditionMet(Predicate<TYPE> condition, TYPE value, String errorMessage){
        if(condition.test(value)){
            throw new BankApplicationException(errorMessage);
        }
    }
}
