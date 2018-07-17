package com.cedaniel200.practice.domain.calculator;

public class CalculatorDefault implements Calculator {

    @Override
    public int add(int numberA, int numberB) {
        return numberA + numberB;
    }

    @Override
    public int divide(int dividend, int divider) throws ArithmeticException {
        return dividend / divider;
    }
}
