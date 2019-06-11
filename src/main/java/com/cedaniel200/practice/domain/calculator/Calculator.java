package com.cedaniel200.practice.domain.calculator;

import com.cedaniel200.practice.domain.calculator.operation.Adder;
import com.cedaniel200.practice.domain.calculator.operation.Divider;
import com.cedaniel200.practice.domain.calculator.operation.Multiplier;
import com.cedaniel200.practice.domain.calculator.operation.Subtractor;

public abstract class Calculator implements Adder, Subtractor, Multiplier, Divider {

}
