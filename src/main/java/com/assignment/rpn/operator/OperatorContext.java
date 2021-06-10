package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.calculator.StackStatesHolder;

public interface OperatorContext<T> {

    OperandStack<T> getStack();

    StackStatesHolder<T> getStates();
}
