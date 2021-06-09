package com.assignment.rpn.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorStackTest {

    private CalculatorStack<BigDecimal> stack;

    @BeforeEach
    public void setup() {
        stack = new CalculatorStack<>();
    }

    @Test
    public void createCopyOfStack() {
        stack.push(BigDecimal.ZERO);
        stack.push(BigDecimal.ONE);
        assertEquals("0 1", new CalculatorStack<>(stack).toString());
    }

    @Test
    public void pushToStack() {
        stack.push(BigDecimal.ZERO);
        assertEquals("0", stack.toString());

        stack.push(BigDecimal.ONE);
        assertEquals("0 1", stack.toString());
    }

    @Test
    public void popFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void popFromNonEmptyStack() {
        stack.push(BigDecimal.ZERO);
        stack.push(BigDecimal.ONE);
        BigDecimal result1 = stack.pop();
        assertEquals("1", result1.toString());
        assertEquals("0", stack.toString());

        BigDecimal result2 = stack.pop();
        assertEquals("0", result2.toString());
        assertEquals("", stack.toString());
    }

    @Test
    public void clearStack() {
        stack.clear();
        assertEquals("", stack.toString());

        stack.push(BigDecimal.ONE);
        stack.clear();
        assertEquals("", stack.toString());
    }

    @AfterEach
    public void tearDown() {
        stack.clear();
    }
}
