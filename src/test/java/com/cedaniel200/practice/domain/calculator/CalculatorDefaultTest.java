package com.cedaniel200.practice.domain.calculator;

import com.cedaniel200.practice.domain.calculator.operation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorDefaultTest {

    private Adder adder;
    private Subtractor subtractor;
    private Multiplier multiplier;
    private Divider divider;
    private Calculator calculator;

    @BeforeEach
    void setup(){
        adder = new AdderDefault();
        subtractor = new SubtractorDefault();
        multiplier = new MultiplierDefault();
        divider = new DividerDefault();
        calculator = new CalculatorDefault(adder, subtractor, multiplier, divider);
    }

    @Test
    void mustBeSuccessfulIfAddPositiveIntegerNumbers(){
        int expected = 10;
        int actual = calculator.add(5, 5);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "-10, 30, 20", "15, -5, 10", "-5, -10, -15"})
    void mustBeSuccessfulIfAddIntegerNumbersWithParam(int firstNumber, int secondNumber, int expectedValue){
        int actual = calculator.add(firstNumber, secondNumber);
        assertEquals(expectedValue, actual);
    }

    @Test
    void mustBeSuccessfulIfAddNegativeIntegerNumbers(){
        int expected = -6;
        int actual = calculator.add(-3, -3);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfSubtractPositiveIntegerNumbers(){
        int expected = -6;
        int actual = calculator.subtract(10, 16);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfSubtractNegativeIntegerNumbers(){
        int expected = 0;
        int actual = calculator.subtract(-3, -3);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfMultiplyPositiveIntegerNumbers(){
        int expected = 15;
        int actual = calculator.multiply(3, 5);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfMultiplyNegativeIntegerNumbers(){
        int expected = 9;
        int actual = calculator.multiply(-3, -3);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfMultiplyByZero(){
        int expected = 0;
        int actual = calculator.multiply(2, 0);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfDividePositiveIntegerNumbers(){
        int expected = 3;
        int actual = calculator.divide(9, 3);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfDivideNegativeIntegerNumbers(){
        int expected = 3;
        int actual = calculator.divide(-9, -3);
        assertEquals(expected, actual);
    }

    @Test
    void mustBeSuccessfulIfThrowAnException(){
        assertThrows(ArithmeticException.class, () -> calculator.divide(8, 0));
    }

}
