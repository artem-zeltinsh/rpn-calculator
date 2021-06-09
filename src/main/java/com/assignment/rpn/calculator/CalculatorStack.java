package com.assignment.rpn.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of RPN calculator stack that can contain real numbers.
 *
 * @param <T> type that represents real numbers
 */
public class CalculatorStack<T> implements OperandStack<T> {

    private final Deque<T> stack = new ArrayDeque<>();

    public CalculatorStack() {
    }

    public CalculatorStack(OperandStack<T> operandStack) {
        for (Iterator<T> it = operandStack.descendingIterator(); it.hasNext();){
            this.push(it.next());
        }
    }

    /**
     * Pops a number from the top of the stack.
     *
     * @return the number at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.pop();
    }

    @Override
    public void push(T number) {
        stack.push(number);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }

    @Override
    public Iterator<T> descendingIterator() {
        return stack.descendingIterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Iterator<T> it = stack.descendingIterator(); it.hasNext(); ) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
