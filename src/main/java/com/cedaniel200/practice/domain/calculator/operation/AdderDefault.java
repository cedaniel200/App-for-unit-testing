package com.cedaniel200.practice.domain.calculator.operation;

public class AdderDefault implements Adder {
    @Override
    public int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
