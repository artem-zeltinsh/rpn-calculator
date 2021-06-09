package com.assignment.rpn.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackStateHolder<T> {

    private final Deque<OperandStack<T>> states = new ArrayDeque<>();

    public OperandStack<T> pop() {
        return states.pop();
    }

    public void push(OperandStack<T> stack) {
        states.push(stack);
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}
