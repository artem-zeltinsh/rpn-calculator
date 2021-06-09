package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorStack;
import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.calculator.StackStateHolder;
import com.assignment.rpn.operator.InsufficientParametersException;
import com.assignment.rpn.operator.OperatorContext;
import com.assignment.rpn.operator.Operators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperatorsTest {

    private CalculatorStack<BigDecimal> stack;

    private StackStateHolder<BigDecimal> states;

    private OperatorContext<BigDecimal> context;

    @BeforeEach
    public void setup() {
        stack = new CalculatorStack<>();
        states = new StackStateHolder<>();

        context = new OperatorContext<>() {
            @Override
            public OperandStack<BigDecimal> getStack() {
                return stack;
            }

            @Override
            public StackStateHolder<BigDecimal> getStates() {
                return states;
            }
        };
    }

    @Test
    public void addNumbers() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(1));
        stack.push(BigDecimal.valueOf(2));
        Operators.ADD.apply(context);
        assertEquals("3", stack.toString());
    }


    @Test
    public void subtractNumbers() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(7));
        stack.push(BigDecimal.valueOf(8));
        Operators.SUBTRACT.apply(context);
        assertEquals("-1", stack.toString());
    }

    @Test
    public void multiplyNumbers() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(5));
        stack.push(BigDecimal.valueOf(20));
        Operators.MULTIPLY.apply(context);
        assertEquals("100", stack.toString());
    }

    @Test
    public void divideNumbers() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(42));
        stack.push(BigDecimal.valueOf(4));
        Operators.DIVIDE.apply(context);
        assertEquals("10.5", stack.toString());
    }

    @Test
    public void sqrt() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(2));
        Operators.SQRT.apply(context);
        assertEquals("1.4142135623731", stack.toString());
    }

    @Test
    public void clear() throws InsufficientParametersException{
        stack.push(BigDecimal.ZERO);
        context.getStates().push(new CalculatorStack<>(stack));
        stack.push(BigDecimal.ONE);
        Operators.CLEAR.apply(context);
        assertEquals("", stack.toString());
    }

    @Test
    public void undoNumber() throws InsufficientParametersException{
        stack.push(BigDecimal.ZERO);
        context.getStates().push(new CalculatorStack<>(stack));
        stack.push(BigDecimal.ONE);

        Operators.UNDO.apply(context);
        assertEquals("0", stack.toString());
    }

    @Test
    public void undoOperator() throws InsufficientParametersException{
        stack.push(BigDecimal.valueOf(1));
        stack.push(BigDecimal.valueOf(2));
        Operators.ADD.apply(context);
        assertEquals("3", stack.toString());

        Operators.UNDO.apply(context);
        assertEquals("1 2", stack.toString());
    }

    @Test
    public void insufficientParameters() {
        assertThrows(InsufficientParametersException.class, () -> Operators.ADD.apply(context));
    }

    @AfterEach
    public void tearDown() {
        stack.clear();
    }
}
