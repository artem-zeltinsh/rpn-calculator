package com.assignment.rpn.calculator;

import com.assignment.rpn.format.CommonStackFormatter;
import com.assignment.rpn.format.StackFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static com.assignment.rpn.operator.Operators.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    private StackFormatter<BigDecimal> formatter;

    @BeforeEach
    public void setup() {
        calculator = new Calculator(
                Set.of(ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT, UNDO, CLEAR));
        formatter = new CommonStackFormatter(10);
    }

    @Test
    public void addNumbers() throws Exception {
        calculator.calculate("5 2");
        assertEquals("5 2", formatter.format(calculator.getStack()));
    }

    @Test
    public void squareRootFromAPerfectSquare() throws CalculatorException {
        calculator.calculate("25 sqrt");
        assertEquals("5", formatter.format(calculator.getStack()));
    }

    @Test
    public void squareRootFromNotAPerfectSquare() throws CalculatorException {
        calculator.calculate("2 sqrt");
        assertEquals("1.4142135623", formatter.format(calculator.getStack()));
    }

    @Test
    public void subtractNumbers() throws CalculatorException {
        calculator.calculate("5 2 -");
        assertEquals("3", formatter.format(calculator.getStack()));

        calculator.calculate("3 - ");
        assertEquals("0", formatter.format(calculator.getStack()));
    }

    @Test
    public void subtractNumbersWithNegativeResult() throws CalculatorException {
        calculator.calculate("3 4 -");
        assertEquals("-1", formatter.format(calculator.getStack()));
    }


    @Test
    public void emptyInputString() throws CalculatorException {
        calculator.calculate("");
        assertEquals("", formatter.format(calculator.getStack()));
    }

    @Test
    public void clearStackWhenItHasOneNumber() throws CalculatorException {
        calculator.calculate("0");
        assertEquals("0", formatter.format(calculator.getStack()));

        calculator.calculate("clear");
        assertEquals("", formatter.format(calculator.getStack()));
    }

    @Test
    public void clearStackWhenItHasMultipleNumbers() throws CalculatorException {
        calculator.calculate("1 2 3");
        assertEquals("1 2 3", formatter.format(calculator.getStack()));

        calculator.calculate("clear");
        assertEquals("", formatter.format(calculator.getStack()));
    }

    @Test
    public void undoNumbersOnTheStack() throws CalculatorException {
        calculator.calculate("5 4 3 2");
        calculator.calculate("undo undo *");
        assertEquals("20", formatter.format(calculator.getStack()));
    }

    @Test
    public void undoPreviousOperation() throws CalculatorException {
        calculator.calculate("20");
        calculator.calculate("5 *");
        calculator.calculate("undo");
        assertEquals("20 5", formatter.format(calculator.getStack()));
    }

    @Test
    public void undoEmptyStack() throws CalculatorException {
        calculator.calculate("undo");
        assertEquals("", formatter.format(calculator.getStack()));
    }

    @Test
    public void multiplyNumbers() throws CalculatorException {
        calculator.calculate("7 6");
        calculator.calculate("*");
        assertEquals("42", formatter.format(calculator.getStack()));
    }

    @Test
    public void multiplyMultipleNumbers() throws CalculatorException {
        calculator.calculate("1 2 3 4 5");
        calculator.calculate("* * * *");
        assertEquals("120", formatter.format(calculator.getStack()));
    }

    @Test
    public void divideNumbers() throws CalculatorException {
        calculator.calculate("7 12 2 /");
        assertEquals("7 6", formatter.format(calculator.getStack()));
    }

    @Test
    public void divideNumbersWithDecimalResult() throws CalculatorException {
        calculator.calculate("42");
        calculator.calculate("4 /");
        assertEquals("10.5", formatter.format(calculator.getStack()));
    }

    @Test
    public void divisionByZero() {
        Exception e = assertThrows(CalculatorException.class, () -> calculator.calculate("5 0 /"));

        assertEquals("operator / (position: 5): Division by zero", e.getMessage());
        assertEquals("5 0", formatter.format(calculator.getStack()));
    }

    @Test
    public void insufficientParameters() {
        Exception e = assertThrows(CalculatorException.class,
                () -> calculator.calculate("1 2 3 * 5 + * * 6 5"));
        assertEquals("operator * (position: 15): insufficient parameters", e.getMessage());
        assertEquals("11", formatter.format(calculator.getStack()));
    }

    @Test
    public void invalidParameter() {
        Exception e = assertThrows(CalculatorException.class,
                () -> calculator.calculate("1 2 + ="));
        assertEquals("field = (position: 7): invalid parameter", e.getMessage());
        assertEquals("3", formatter.format(calculator.getStack()));
    }
}
