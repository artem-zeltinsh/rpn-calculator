package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorStack;
import com.assignment.rpn.calculator.OperandStack;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractOperator<T> implements Operator<T> {

    private final String symbol;

    AbstractOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract void apply(OperatorContext<T> context) throws InsufficientParametersException;

    protected void apply(BinaryOperator<T> binaryOperator, OperatorContext<T> context)
            throws InsufficientParametersException {
        OperandStack<T> stack = context.getStack();
        checkOperandParameters(stack.size(), 2);

        OperandStack<T> recordedState = new CalculatorStack<>(stack);

        T right = stack.pop();
        T left = stack.pop();
        try {
            stack.push(binaryOperator.apply(left, right));
        } catch (ArithmeticException e) {
            stack.push(left);
            stack.push(right);
            throw e;
        }

        context.getStates().push(recordedState);
    }

    protected void apply(UnaryOperator<T> unaryOperator, OperatorContext<T> context)
            throws InsufficientParametersException {
        OperandStack<T> stack = context.getStack();
        checkOperandParameters(stack.size(), 1);

        OperandStack<T> recordedState = new CalculatorStack<>(stack);

        T op = stack.pop();
        try {
            stack.push(unaryOperator.apply(op));
        } catch (ArithmeticException e) {
            stack.push(op);
            throw e;
        }

        context.getStates().push(recordedState);
    }

    protected void checkOperandParameters(int stackSize, int numberOfOperands) throws InsufficientParametersException {
        if (stackSize < numberOfOperands) {
            throw new InsufficientParametersException("insufficient parameters");
        }
    }

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
