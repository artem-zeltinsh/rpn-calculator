package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.calculator.StackStateHolder;

public interface OperatorContext<T> {

    OperandStack<T> getStack();

    StackStateHolder<T> getStates();
}
