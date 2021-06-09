package com.assignment.rpn.calculator;

import com.assignment.rpn.operator.*;
import com.assignment.rpn.view.CommonStackFormatter;
import com.assignment.rpn.view.StackFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calculator expects to receive strings containing whitespace separated list of numbers and operators.
 */
public class Calculator {

    private static final Pattern inputFieldPattern = Pattern.compile("\\S+");

    /**
     * Operators supported by this calculator.
     */
    private final Map<String, Operator<BigDecimal>> operators = new HashMap<>();

    /**
     * The stack that can contain real numbers.
     */
    private final OperandStack<BigDecimal> stack = new CalculatorStack<>();

    private final StackStateHolder<BigDecimal> stateHolder = new StackStateHolder<>();

    private final StackFormatter<BigDecimal> formatter;

    public Calculator(Set<Operator<BigDecimal>> operators) {
        this(operators, new CommonStackFormatter(10));
    }

    public Calculator(Set<Operator<BigDecimal>> operators, StackFormatter<BigDecimal> formatter) {
        operators.forEach(op -> this.operators.put(op.getSymbol(), op));
        this.formatter = formatter;
    }

    public OperandStack<BigDecimal> getStack() {
        return new CalculatorStack<>(stack);
    }

    /**
     * Method expects to receive string containing whitespace separated list of numbers and operators.
     *
     * @param input string containing whitespace separated list of numbers and operators
     * @return the calculator stack contents as a list
     * @throws CalculatorException invalid input field
     */
    public OperandStack<BigDecimal> interpret(final String input) throws CalculatorException {
        Matcher fieldMatcher = inputFieldPattern.matcher(input);
        while (fieldMatcher.find()) {
            final String nextField = fieldMatcher.group();
            final int position = fieldMatcher.start() + 1;
            try {
                if (operators.containsKey(nextField)) {
                    final Operator<BigDecimal> operator = operators.get(nextField);
                    operator.apply(operatorContext());
                } else {
                    final OperandStack<BigDecimal> recordedState = new CalculatorStack<>(stack);
                    stack.push(new BigDecimal(nextField).setScale(15, RoundingMode.HALF_DOWN));
                    stateHolder.push(recordedState);
                }
            } catch (ArithmeticException | InsufficientParametersException e) {
                throw new CalculatorException(
                        errorMessage("operator", nextField, position, e.getMessage()));
            } catch (NumberFormatException e) {
                throw new CalculatorException(
                        errorMessage("field", nextField, position, "invalid parameter"));
            }
        }
        return getStack();
    }

    private OperatorContext<BigDecimal> operatorContext() {
        return new OperatorContext<>() {
            @Override
            public OperandStack<BigDecimal> getStack() {
                return stack;
            }

            @Override
            public StackStateHolder<BigDecimal> getStates() {
                return stateHolder;
            }
        };
    }

    private static String errorMessage(String fieldName, String fieldValue, int position, String message) {
        return String.format("%s %s (position: %d): %s", fieldName, fieldValue, position, message);
    }

    @Override
    public String toString() {
        return formatter.format(stack);
    }
}
