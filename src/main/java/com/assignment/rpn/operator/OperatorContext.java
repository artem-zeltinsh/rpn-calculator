package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.calculator.StackStatesHolder;

/**
 * Operator context can provide the current state of the operand stack and its previous states.
 * @param <T> numeric type that represents the stack operands
 */
public interface OperatorContext<T> {

    /**
     * Returns the current state of the operand stack.
     * @return the current state of the operand stack
     */
    OperandStack<T> getStack();

    /**
     * Returns a holder of previous states of the operand stack.
     * @return holder of previous states of the operand stack
     */
    StackStatesHolder<T> getStates();
}
