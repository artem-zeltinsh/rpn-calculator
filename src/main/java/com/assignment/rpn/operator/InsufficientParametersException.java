package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorException;

/**
 * Thrown to indicate that a stack has insufficient parameters to proceed.
 */
public class InsufficientParametersException extends CalculatorException {
    public InsufficientParametersException(String message) {
        super(message);
    }
}
