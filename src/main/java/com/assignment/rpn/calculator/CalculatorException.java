package com.assignment.rpn.calculator;

/**
 * Thrown to indicate that RPN calculator encounter an exceptional condition.
 */
public class CalculatorException extends Exception {

    public CalculatorException(String message) {
        super(message);
    }
}
