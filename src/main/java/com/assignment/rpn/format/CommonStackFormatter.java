package com.assignment.rpn.format;

import com.assignment.rpn.calculator.OperandStack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

/**
 * Formats numbers stored on the operand stack as a space-separated list. Decimal numbers
 * are formatted to 10 decimal places (or less if it causes no loss of precision).
 */
public class CommonStackFormatter implements StackFormatter<BigDecimal> {

    private final int scale;

    public CommonStackFormatter(int scale) {
        this.scale = scale;
    }

    public String format(OperandStack<BigDecimal> stack) {
        StringBuilder result = new StringBuilder();
        for (Iterator<BigDecimal> it = stack.descendingIterator(); it.hasNext(); ) {
            BigDecimal next = it.next().setScale(scale, RoundingMode.FLOOR).stripTrailingZeros();
            result.append(next.toPlainString());
            if (it.hasNext()) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
