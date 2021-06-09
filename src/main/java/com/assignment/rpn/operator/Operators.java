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

    public static final Operator<BigDecimal> ADD = new BinaryOperator<>("+", BigDecimal::add);
    public static final Operator<BigDecimal> SUBTRACT = new BinaryOperator<>("-", BigDecimal::subtract);
    public static final Operator<BigDecimal> MULTIPLY = new BinaryOperator<>("*", BigDecimal::multiply);
    public static final Operator<BigDecimal> DIVIDE = new BinaryOperator<>("/", BigDecimal::divide);
    public static final Operator<BigDecimal> SQRT = new UnaryOperator<>(
            "sqrt", d -> d.sqrt(Operators.DECIMAL_CONTEXT));

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

    public static void checkSufficientOperands(int requiredNumberOfOperands, int stackSize)
            throws InsufficientParametersException {
        if (stackSize < requiredNumberOfOperands) {
            throw new InsufficientParametersException("insufficient parameters");
        }
    }
}
