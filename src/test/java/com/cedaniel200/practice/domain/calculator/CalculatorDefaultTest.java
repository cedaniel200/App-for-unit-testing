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
    void add_twoPositiveIntegers_shouldReturnExpectedSum(){
        // Arrange
        int expected = 10;

        // Act
        int actual = calculator.add(5, 5);

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "-10, 30, 20", "15, -5, 10", "-5, -10, -15"})
    void add_twoIntegers_shouldReturnExpectedResult(int firstNumber, int secondNumber, int expectedValue){
        // Act
        int actual = calculator.add(firstNumber, secondNumber);

        // Assert
        assertEquals(expectedValue, actual);
    }

    @Test
    void add_twoNegativeIntegers_shouldReturnExpectedResult(){
        // Arrange
        int expected = -6;

        // Act
        int actual = calculator.add(-3, -3);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void subtract_twoIntegers_shouldReturnExpectedDifference(){
        // Arrange
        int expected = -6;

        // Act
        int actual = calculator.subtract(10, 16);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void subtract_negativeIntegers_shouldReturnExpectedDifference(){
        // Arrange
        int expected = 0;

        // Act
        int actual = calculator.subtract(-3, -3);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void multiply_twoIntegers_shouldReturnProduct(){
        // Arrange
        int expected = 15;

        // Act
        int actual = calculator.multiply(3, 5);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void multiply_negativeIntegers_shouldReturnProduct(){
        // Arrange
        int expected = 9;

        // Act
        int actual = calculator.multiply(-3, -3);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void multiply_anyNumberWithZero_shouldReturnZero(){
        // Arrange
        int expected = 0;

        // Act
        int actual = calculator.multiply(2, 0);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void divide_twoIntegers_shouldReturnQuotient(){
        // Arrange
        int expected = 3;

        // Act
        int actual = calculator.divide(9, 3);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void divide_negativeIntegers_shouldReturnCorrectResult(){
        // Arrange
        int expected = 3;

        // Act
        int actual = calculator.divide(-9, -3);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void divide_integerByZero_shouldThrowArithmeticException(){
        // Primera forma de validar si se lanza una excepción
        assertThrows(ArithmeticException.class, () -> calculator.divide(8, 0));
    }

}
