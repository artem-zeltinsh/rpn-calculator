package com.assignment.rpn.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.assignment.rpn.operator.Operators.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator(
                Set.of(ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT, UNDO, CLEAR));
    }

    @Test
    public void addNumbers() throws Exception {
        calculator.interpret("5 2");
        assertEquals("5 2", calculator.toString());
    }

    @Test
    public void squareRootFromAPerfectSquare() throws CalculatorException {
        calculator.interpret("25 sqrt");
        assertEquals("5", calculator.toString());
    }

    @Test
    public void squareRootFromNotAPerfectSquare() throws CalculatorException {
        calculator.interpret("2 sqrt");
        assertEquals("1.4142135623", calculator.toString());
    }

    @Test
    public void subtractNumbers() throws CalculatorException {
        calculator.interpret("5 2 -");
        assertEquals("3", calculator.toString());

        calculator.interpret("3 - ");
        assertEquals("0", calculator.toString());
    }

    @Test
    public void subtractNumbersWithNegativeResult() throws CalculatorException {
        calculator.interpret("3 4 -");
        assertEquals("-1", calculator.toString());
    }


    @Test
    public void emptyInputString() throws CalculatorException {
        calculator.interpret("");
        assertEquals("", calculator.toString());
    }

    @Test
    public void clearStackWhenItHasOneNumber() throws CalculatorException {
        calculator.interpret("0");
        assertEquals("0", calculator.toString());

        calculator.interpret("clear");
        assertEquals("", calculator.toString());
    }

    @Test
    public void clearStackWhenItHasMultipleNumbers() throws CalculatorException {
        calculator.interpret("1 2 3");
        assertEquals("1 2 3", calculator.toString());

        calculator.interpret("clear");
        assertEquals("", calculator.toString());
    }

    @Test
    public void undoNumbersOnTheStack() throws CalculatorException {
        calculator.interpret("5 4 3 2");
        calculator.interpret("undo undo *");
        assertEquals("20", calculator.toString());
    }

    @Test
    public void undoPreviousOperation() throws CalculatorException {
        calculator.interpret("20");
        calculator.interpret("5 *");
        calculator.interpret("undo");
        assertEquals("20 5", calculator.toString());
    }

    @Test
    public void undoEmptyStack() throws CalculatorException {
        calculator.interpret("undo");
        assertEquals("", calculator.toString());
    }

    @Test
    public void multiplyNumbers() throws CalculatorException {
        calculator.interpret("7 6");
        calculator.interpret("*");
        assertEquals("42", calculator.toString());
    }

    @Test
    public void multiplyMultipleNumbers() throws CalculatorException {
        calculator.interpret("1 2 3 4 5");
        calculator.interpret("* * * *");
        assertEquals("120", calculator.toString());
    }

    @Test
    public void divideNumbers() throws CalculatorException {
        calculator.interpret("7 12 2 /");
        assertEquals("7 6", calculator.toString());
    }

    @Test
    public void divideNumbersWithDecimalResult() throws CalculatorException {
        calculator.interpret("42");
        calculator.interpret("4 /");
        assertEquals("10.5", calculator.toString());
    }

    @Test
    public void divisionByZero() {
        Exception e = assertThrows(CalculatorException.class, () -> calculator.interpret("5 0 /"));

        assertEquals("operator / (position: 5): Division by zero", e.getMessage());
        assertEquals("5 0", calculator.toString());
    }

    @Test
    public void insufficientParameters() {
        Exception e = assertThrows(CalculatorException.class,
                () -> calculator.interpret("1 2 3 * 5 + * * 6 5"));
        assertEquals("operator * (position: 15): insufficient parameters", e.getMessage());
        assertEquals("11", calculator.toString());
    }

    @Test
    public void invalidParameter() {
        Exception e = assertThrows(CalculatorException.class,
                () -> calculator.interpret("1 2 + ="));
        assertEquals("field = (position: 7): invalid parameter", e.getMessage());
        assertEquals("3", calculator.toString());
    }
}
