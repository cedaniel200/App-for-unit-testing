package com.cedaniel200.practice.domain.calculator.operation;

public class SubtractorDefault implements Subtractor {
    @Override
    public int subtract(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }
}
