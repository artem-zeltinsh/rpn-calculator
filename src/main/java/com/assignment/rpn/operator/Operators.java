package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.calculator.StackStateHolder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Iterator;

/**
 * Utility class for operators.
 * <p>
 * The {@link #ADD}, {@link #SUBTRACT}, {@link #MULTIPLY}, {@link #DIVIDE} operators perform
 * addition, subtraction, multiplication and division respectively on the top two items from the stack.
 * <p>
 * {@link #SQRT} performs a square root on the top item from the stack.
 * <p>
 * {@link #CLEAR} removes all items from the stack.
 * <p>
 * {@link #UNDO} undoes the previous operation. When performed twice can undo two operations.
 */
public final class Operators {

    /**
     * Defines a decimal precision for real numbers.
     */
    private static final MathContext DECIMAL_CONTEXT = new MathContext(15);

    private Operators() {
    }

    public static final Operator<BigDecimal> ADD = new AbstractOperator<>("+") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) throws InsufficientParametersException {
            apply(BigDecimal::add, context);
        }
    };

    public static final Operator<BigDecimal> SUBTRACT = new AbstractOperator<>("-") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) throws InsufficientParametersException {
            apply(BigDecimal::subtract, context);
        }
    };

    public static final Operator<BigDecimal> MULTIPLY = new AbstractOperator<>("*") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) throws InsufficientParametersException {
            apply(BigDecimal::multiply, context);
        }
    };

    public static final Operator<BigDecimal> DIVIDE = new AbstractOperator<>("/") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) throws InsufficientParametersException {
            apply(BigDecimal::divide, context);
        }
    };

    public static final Operator<BigDecimal> SQRT = new AbstractOperator<>("sqrt") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) throws InsufficientParametersException {
            apply(bigDecimal -> bigDecimal.sqrt(Operators.DECIMAL_CONTEXT), context);
        }
    };

    public static final Operator<BigDecimal> CLEAR = new AbstractOperator<>("clear") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) {
            context.getStack().clear();
        }
    };

    public static final Operator<BigDecimal> UNDO = new AbstractOperator<>("undo") {
        @Override
        public void apply(OperatorContext<BigDecimal> context) {
            StackStateHolder<BigDecimal> states = context.getStates();
            if (!states.isEmpty()) {
                OperandStack<BigDecimal> stack = context.getStack();
                stack.clear();
                OperandStack<BigDecimal> previousState = states.pop();
                for (Iterator<BigDecimal> it = previousState.descendingIterator(); it.hasNext(); ) {
                    stack.push(it.next());
                }
            }
        }
    };
}
