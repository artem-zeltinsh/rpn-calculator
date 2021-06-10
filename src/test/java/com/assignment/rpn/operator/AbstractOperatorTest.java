package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorStack;
import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.calculator.StackStatesHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.FLOOR;

public abstract class AbstractOperatorTest {

    static final MathContext DECIMAL_CONTEXT = new MathContext(15, FLOOR);

    CalculatorStack<BigDecimal> stack;
    StackStatesHolder<BigDecimal> states;
    OperatorContext<BigDecimal> context;

    @BeforeEach
    public void setup() {
        stack = new CalculatorStack<>();
        states = new StackStatesHolder<>();
        context = new OperatorContext<>() {
            @Override
            public OperandStack<BigDecimal> getStack() {
                return stack;
            }

            @Override
            public StackStatesHolder<BigDecimal> getStates() {
                return states;
            }
        };
    }

    @AfterEach
    public void tearDown() {
        stack.clear();
    }
}
