package com.cedaniel200.practice.domain.calculator;

import com.cedaniel200.practice.domain.calculator.operation.Adder;
import com.cedaniel200.practice.domain.calculator.operation.Divider;
import com.cedaniel200.practice.domain.calculator.operation.Multiplier;
import com.cedaniel200.practice.domain.calculator.operation.Subtractor;

public class CalculatorDefault extends Calculator {

    private Adder adder;
    private Subtractor subtractor;
    private Multiplier multiplier;
    private Divider divider;

    public CalculatorDefault(Adder adder, Subtractor subtractor, Multiplier multiplier, Divider divider) {
        this.adder = adder;
        this.subtractor = subtractor;
        this.multiplier = multiplier;
        this.divider = divider;
    }

    @Override
    public int add(int firstNumber, int secondNumber) {
        return adder.add(firstNumber, secondNumber);
    }

    @Override
    public int subtract(int firstNumber, int secondNumber) {
        return subtractor.subtract(firstNumber, secondNumber);
    }

    @Override
    public int multiply(int firstNumber, int secondNumber) {
        return multiplier.multiply(firstNumber, secondNumber);
    }

    @Override
    public int divide(int dividend, int divider) throws ArithmeticException {
        return this.divider.divide(dividend, divider);
    }
}
