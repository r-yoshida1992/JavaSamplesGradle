package com.example.calc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class Calculator {

    /**
     * 数値加算
     */
    public static Number sum(Number... num) {
        return calculate(BigDecimal::add, num);
    }

    /**
     * 数値減算
     */
    public static Number subtract(Number... num) {
        return calculate(BigDecimal::subtract, num);
    }

    /**
     * 数値乗算
     */
    public static Number multiply(Number... num) {
        return calculate(BigDecimal::multiply, num);
    }

    /**
     * 数値除算
     */
    public static Number divide(Number... num) {
        return calculate(BigDecimal::divide, num);
    }

    /**
     * 計算用の共通関数
     */
    private static Number calculate(BinaryOperator<BigDecimal> callback, Number... num) {
        return Arrays
                .stream(num)
                .filter(Objects::nonNull)
                .map(n -> BigDecimal.valueOf(
                        n instanceof Float
                                ? Double.parseDouble(String.valueOf(n))
                                : n.doubleValue()))
                .reduce(callback)
                .orElseThrow()
                .stripTrailingZeros();
    }
}
