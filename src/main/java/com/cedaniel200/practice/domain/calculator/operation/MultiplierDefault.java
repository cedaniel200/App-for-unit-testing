package com.cedaniel200.practice.domain.calculator.operation;

public class MultiplierDefault implements Multiplier {
    @Override
    public int multiply(int firstNumber, int secondNumber) {
        return firstNumber *secondNumber;
    }
}
