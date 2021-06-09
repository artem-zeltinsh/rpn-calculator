package com.assignment.rpn.calculator;

import java.util.Iterator;

/**
 * RPN calculator stack that can contain real numbers
 * @param <T> numeric type that represents the stack operands
 */
public interface OperandStack<T> extends Iterable<T> {

    /**
     * Pops a number from the top of the stack.
     * @return the number at the top of the stack.
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    T pop();

    /**
     * Pushes a number onto the stack.
     * @param number the number to push
     */
    void push(T number);

    /**
     * Removes all numbers from the stack.
     */
    void clear();

    /**
     * Returns the cardinality of the stack.
     * @return the cardinality of the stack
     */
    int size();

    /**
     * Returns an iterator over the elements in this stack in proper sequence.
     * @return an iterator over the elements in this stack in proper sequence
     */
    Iterator<T> iterator();

    /**
     * Returns an iterator over the elements in this stack in reverse sequential order.
     * @return Returns an iterator over the elements in this stack in reverse sequential order
     */
    Iterator<T> descendingIterator();
}
