package com.assignment.rpn.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnaryOperatorTest extends AbstractOperatorTest {

    @Test
    public void sqrt() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(2));
        Operators.SQRT.apply(context);
        assertEquals("1.4142135623731", stack.toString());
    }

    @Test
    public void negateOperator() throws InsufficientParametersException {
        stack.push(BigDecimal.valueOf(5));
        UnaryOperator<BigDecimal> remainderOperator = new UnaryOperator<>("!", BigDecimal::negate);
        remainderOperator.apply(context);
        assertEquals("-5", stack.toString());
    }
}
