package com.assignment.rpn.calculator;

/**
 * Thrown to indicate that an exceptional condition occurred during the calculation.
 */
public class CalculatorException extends Exception {

    public CalculatorException(String message) {
        super(message);
    }
}
