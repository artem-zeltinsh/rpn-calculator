package com.assignment.rpn.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOperatorTest extends AbstractOperatorTest {

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
    public void remainderOperator() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(10));
        stack.push(BigDecimal.valueOf(3));

        BinaryOperator<BigDecimal> remainderOperator = new BinaryOperator<>(
                "%", (d1, d2) -> d1.remainder(d2, DECIMAL_CONTEXT));
        remainderOperator.apply(context);
        assertEquals("1", stack.toString());
    }
}
