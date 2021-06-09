package com.assignment.rpn.view;

import com.assignment.rpn.calculator.CalculatorStack;
import com.assignment.rpn.calculator.OperandStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.FLOOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonStackFormatterTest {

    private static final MathContext DECIMAL_CONTEXT = new MathContext(15, FLOOR);

    private CommonStackFormatter formatter;

    private OperandStack<BigDecimal> stack;

    @BeforeEach
    public void setup() {
        this.formatter = new CommonStackFormatter(10);
        this.stack = new CalculatorStack<>();
    }

    @Test
    public void formatIntegers() {
        stack.push(BigDecimal.valueOf(1));
        stack.push(BigDecimal.valueOf(2));
        stack.push(BigDecimal.valueOf(3));
        assertEquals("1 2 3", formatter.format(stack));
    }

    @Test
    public void formatBigIntegers() {
        stack.push(BigDecimal.valueOf(Integer.MAX_VALUE));
        stack.push(BigDecimal.valueOf(Integer.MIN_VALUE));
        assertEquals("2147483647 -2147483648", formatter.format(stack));
    }

    @Test
    public void formatDecimals() {
        BigDecimal testDecimal = BigDecimal.valueOf(2).setScale(15, FLOOR);
        stack.push(testDecimal.sqrt(DECIMAL_CONTEXT));
        assertEquals("1.4142135623", formatter.format(stack));
    }

    @Test
    public void formatBigDecimals() {
        BigDecimal testBigDecimal = new BigDecimal(Integer.MAX_VALUE).setScale(15, FLOOR);
        stack.push(testBigDecimal.sqrt(DECIMAL_CONTEXT));
        assertEquals("46340.9500010519", formatter.format(stack));
    }

    @AfterEach
    public void tearDown() {
        stack.clear();
    }
}
