package com.assignment.rpn.operator;

/**
 * Operators pop their parameters from RPN calculator stack and push their result back to the stack.
 */
public interface Operator<T> {

    void apply(OperatorContext<T> context) throws InsufficientParametersException;

    String getSymbol();
}
