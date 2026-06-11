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
    public String add(int firstNumber, int secondNumber){
        return String.format(RESULT_FORMAT, "sum", calculator.add(firstNumber, secondNumber));
    }

    @ShellMethod(value = "subtract two whole numbers")
    public String subtract(int firstNumber, int secondNumber){
        return String.format(RESULT_FORMAT, "subtract", calculator.subtract(firstNumber, secondNumber));
    }

    @ShellMethod(value = "multiply two whole numbers")
    public String multiply(int firstNumber, int secondNumber){
        return String.format(RESULT_FORMAT, "multiply", calculator.multiply(firstNumber, secondNumber));
    }

    @ShellMethod(value = "Divide")
    public String divide(int dividend, int divider){
        return String.format(RESULT_FORMAT, "divide", calculator.divide(dividend, divider));
    }
}
