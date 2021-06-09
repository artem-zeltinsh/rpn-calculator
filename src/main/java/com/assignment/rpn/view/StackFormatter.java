package com.assignment.rpn.view;

import com.assignment.rpn.calculator.OperandStack;

/**
 * Formatter for printing of the calculator operand stack.
 */
public interface StackFormatter<T> {
    String format(OperandStack<T> items);
}
