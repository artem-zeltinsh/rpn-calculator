package com.assignment.rpn.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Implementation of RPN calculator stack that can contain real numbers.
 * @param <T> numeric type that represents the stack operands
 */
public class CalculatorStack<T> implements OperandStack<T> {

    private final Deque<T> state = new ArrayDeque<>();

    public CalculatorStack() {
    }

    public CalculatorStack(OperandStack<T> operandStack) {
        for (Iterator<T> it = operandStack.descendingIterator(); it.hasNext();){
            this.push(it.next());
        }
    }

    @Override
    public T pop() {
        return state.pop();
    }

    @Override
    public void push(T number) {
        state.push(number);
    }

    @Override
    public void clear() {
        state.clear();
    }

    @Override
    public int size() {
        return state.size();
    }

    @Override
    public Iterator<T> iterator() {
        return state.iterator();
    }

    @Override
    public Iterator<T> descendingIterator() {
        return state.descendingIterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Iterator<T> it = state.descendingIterator(); it.hasNext(); ) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
