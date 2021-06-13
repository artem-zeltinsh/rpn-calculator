package com.assignment.rpn.operator;

import java.util.Objects;

/**
 * Skeletal implementation of the {@link Operator} interface.
 * @param <T> the type of operands.
 */
public abstract class AbstractOperator<T> implements Operator<T> {

    private final String symbol;

    public AbstractOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract void apply(OperatorContext<T> context) throws InsufficientParametersException;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        AbstractOperator<?> that = (AbstractOperator<?>) other;
        return symbol.equals(that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
