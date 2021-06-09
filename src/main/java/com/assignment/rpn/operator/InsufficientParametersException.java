package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorException;

/**
 * Thrown to indicate that the stack has insufficient parameters to apply operator.
 */
public class InsufficientParametersException extends CalculatorException {
    public InsufficientParametersException(String message) {
        super(message);
    }
}
