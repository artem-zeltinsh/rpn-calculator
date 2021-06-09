package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorStack;
import com.assignment.rpn.calculator.OperandStack;

public class BinaryOperator<T> extends AbstractOperator<T> {

    private final java.util.function.BinaryOperator<T> binaryOperator;

    public BinaryOperator(String symbol, java.util.function.BinaryOperator<T> binaryOperator) {
        super(symbol);
        this.binaryOperator = binaryOperator;
    }

    @Override
    public void apply(OperatorContext<T> context) throws InsufficientParametersException {
        OperandStack<T> stack = context.getStack();
        Operators.checkSufficientOperands(2, stack.size());

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
}
