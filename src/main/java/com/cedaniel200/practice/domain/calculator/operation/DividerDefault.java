package com.cedaniel200.practice.domain.calculator.operation;

public class DividerDefault implements Divider {
    @Override
    public int divide(int dividend, int divider) throws ArithmeticException {
        return dividend / divider;
    }
}
