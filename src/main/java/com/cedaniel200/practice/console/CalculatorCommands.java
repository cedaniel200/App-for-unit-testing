package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.calculator.Calculator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CalculatorCommands {

    private static final String RESULT_FORMAT = "the result of the %s is: %d";

    private Calculator calculator;

    public CalculatorCommands(Calculator calculator) {
        this.calculator = calculator;
    }

    @ShellMethod(value = "Add two whole numbers")
    public String add(int numberA, int numberB){
        return String.format(RESULT_FORMAT, "sum", calculator.add(numberA, numberB));
    }

    @ShellMethod(value = "Divide")
    public String divide(int dividend, int divider){
        return String.format(RESULT_FORMAT, "divide", calculator.divide(dividend, divider));
    }
}
