package com.cedaniel200.practice.domain;

import com.cedaniel200.practice.domain.calculator.Calculator;
import com.cedaniel200.practice.domain.calculator.CalculatorDefault;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorDefaultTest {

    private Calculator calculator;

    @Before
    public void setup(){
        // Arrange
        calculator = new CalculatorDefault();
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

    //TODO create the necessary unit testings

}
