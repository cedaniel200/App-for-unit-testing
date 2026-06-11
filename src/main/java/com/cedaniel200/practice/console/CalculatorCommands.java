package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.calculator.Calculator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CalculatorCommands {

    private static final String RESULT_FORMAT = "el resultado de la %s es: %d";

    private Calculator calculator;

    public CalculatorCommands(Calculator calculator) {
        this.calculator = calculator;
    }

    @ShellMethod(value = "Sumar dos numeros enteros")
    public String add(int firstNumber, int secondNumber){
        return String.format(RESULT_FORMAT, "suma", calculator.add(firstNumber, secondNumber));
    }

    @ShellMethod(value = "Restar dos numeros enteros")
    public String subtract(int firstNumber, int secondNumber){
        return String.format(RESULT_FORMAT, "resta", calculator.subtract(firstNumber, secondNumber));
    }

    @ShellMethod(value = "Multiplicar dos numeros enteros")
    public String multiply(int firstNumber, int secondNumber){
        return String.format(RESULT_FORMAT, "multiplicacion", calculator.multiply(firstNumber, secondNumber));
    }

    @ShellMethod(value = "Dividir dos numeros enteros")
    public String divide(int dividend, int divider){
        return String.format(RESULT_FORMAT, "division", calculator.divide(dividend, divider));
    }
}
