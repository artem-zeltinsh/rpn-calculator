package com.assignment.rpn.operator;

import com.assignment.rpn.calculator.CalculatorStack;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperatorTest extends AbstractOperatorTest {

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
}
