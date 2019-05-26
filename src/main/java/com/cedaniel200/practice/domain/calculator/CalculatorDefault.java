package com.cedaniel200.practice.domain.calculator;

public class CalculatorDefault implements Calculator {

    @Override
    public int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    @Override
    public int subtract(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    @Override
    public int multiply(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }

    @Override
    public int divide(int dividend, int divider) throws ArithmeticException {
        return dividend / divider;
    }
}
