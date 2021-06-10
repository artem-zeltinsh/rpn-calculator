package com.assignment.rpn.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stores states (versions) of the operand stack. Provides methods to add or remove a version.
 * @param <T> numeric type that represents the stack operands
 */
public class StackStatesHolder<T> {

    /**
     * Versions of the operand stack.
     */
    private final Deque<OperandStack<T>> states = new ArrayDeque<>();

    /**
     * Removes (undo) the latest version of the operand stack and returns it in the result.
     * @return the latest version of the operand stack
     */
    public OperandStack<T> pop() {
        return states.pop();
    }

    /**
     * Saves a new version of the operand stack.
     * @param stack new version of the operand stack
     */
    public void push(OperandStack<T> stack) {
        states.push(stack);
    }

    /**
     * Returns {@code true} if there are no stored versions.
     * @return {@code true} if there are no stored versions.
     */
    public boolean isEmpty() {
        return states.isEmpty();
    }
}
