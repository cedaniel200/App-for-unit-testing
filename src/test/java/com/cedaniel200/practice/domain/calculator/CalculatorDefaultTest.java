package com.cedaniel200.practice.domain.calculator;

import com.cedaniel200.practice.domain.calculator.operation.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class CalculatorDefaultTest {

    private Adder adder;
    private Subtractor subtractor;
    private Multiplier multiplier;
    private Divider divider;
    private Calculator calculator;

    @Before
    public void setup(){
        // Arrange
        adder = new AdderDefault();
        subtractor = new SubtractorDefault();
        multiplier = new MultiplierDefault();
        divider = new DividerDefault();
        calculator = new CalculatorDefault(adder, subtractor, multiplier, divider);
    }

    @Test
    public void mustBeSuccessfulIfAddPositiveIntegerNumbers(){
        // Arrange
        int expected = 10;

        // Act
        int actual = calculator.add(5, 5);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    @Parameters({"1, 2, 3",
            "-10, 30, 20",
            "15, -5, 10",
            "-5, -10, -15" })
    public void mustBeSuccessfulIfAddIntegerNumbersWithParam(int firstNumber, int secondNumber, int expectedValue){
        // Act
        int actual = calculator.add(firstNumber, secondNumber);

        // Assert
        Assert.assertEquals(expectedValue, actual);
    }

    @Test
    public void mustBeSuccessfulIfAddNegativeIntegerNumbers(){
        int expected = -6;

        int actual = calculator.add(-3, -3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfSubtractPositiveIntegerNumbers(){
        int expected = -6;

        int actual = calculator.subtract(10, 16);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfSubtractNegativeIntegerNumbers(){
        int expected = 0;

        int actual = calculator.subtract(-3, -3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfMultiplyPositiveIntegerNumbers(){
        int expected = 15;

        int actual = calculator.multiply(3, 5);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfMultiplyNegativeIntegerNumbers(){
        int expected = 9;

        int actual = calculator.multiply(-3, -3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfMultiplyByZero(){
        int expected = 0;

        int actual = calculator.multiply(2, 0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfDividePositiveIntegerNumbers(){
        int expected = 3;

        int actual = calculator.divide(9, 3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfDivideNegativeIntegerNumbers(){
        int expected = 3;

        int actual = calculator.divide(-9, -3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mustBeSuccessfulIfThrowAnException(){
        // Primera forma de validar si se lanza una excepción
        try {
            int actual = calculator.divide(8, 0);

            fail("Did not throw the Arithmetic Exception");
        }catch (ArithmeticException e){

        }
    }

}
