package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorStack;
import com.assignment.rpn.calculator.OperandStack;

public class UnaryOperator<T> extends AbstractOperator<T> {

    private final java.util.function.UnaryOperator<T> unaryOperator;

    public UnaryOperator(String symbol, java.util.function.UnaryOperator<T> unaryOperator) {
        super(symbol);
        this.unaryOperator = unaryOperator;
    }

    @Override
    public void apply(OperatorContext<T> context) throws InsufficientParametersException {
        OperandStack<T> stack = context.getStack();
        Operators.checkSufficientOperands(1, stack.size());

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
}
