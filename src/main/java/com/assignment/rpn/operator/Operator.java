package com.assignment.rpn.operator;

/**
 * Operator refers to the symbol denoting the operation. Operation is applied to operands
 * that are provide by {@code OperatorContext}.
 */
public interface Operator<T> {

    /**
     * Returns the symbol of the operator.
     * @return the symbol of the operator.
     */
    String getSymbol();

    /**
     * Applies operation to operands provided by the operation context.
     * @param context provides operands for this operation
     * @throws InsufficientParametersException if operator context is missing operands
     */
    void apply(OperatorContext<T> context) throws InsufficientParametersException;
}
