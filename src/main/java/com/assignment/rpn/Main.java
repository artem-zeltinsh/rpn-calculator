package com.assignment.rpn;

import com.assignment.rpn.calculator.Calculator;
import com.assignment.rpn.calculator.CalculatorException;
import com.assignment.rpn.calculator.OperandStack;
import com.assignment.rpn.view.CommonStackFormatter;
import com.assignment.rpn.view.StackFormatter;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

import static com.assignment.rpn.operator.Operators.*;
import static java.lang.System.out;

/**
 * Command-line interface for RPN calculator.
 */
public class Main {

    private final static Set<String> exitCommands = Set.of("exit", "quit");

    public static void main(String[] args) {
        printBanner();

        Calculator calculator = new Calculator(
                Set.of(ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT, UNDO, CLEAR));
        StackFormatter<BigDecimal> formatter = new CommonStackFormatter(10);

        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            final String nextLine = input.nextLine().trim();
            if (exitCommands.contains(nextLine)) {
                break;
            } else if (!nextLine.isBlank()) {
                try {
                    OperandStack<BigDecimal> result = calculator.interpret(nextLine);
                    out.println("stack: " + formatter.format(result));
                } catch (CalculatorException e) {
                    out.println(e.getMessage());
                }

            }
        }

        input.close();
    }

    private static void printBanner() {
        out.println(" ----=-=------------------------------------=-=-=---");
        out.println("/   Welcome to the command-line RPN calculator!   /");
        out.println("--=-=-=------------------------------------=-=----");
        out.println(" Enter " + String.join(", ", exitCommands) + " commands to finish the session.");
        out.println();
    }
}
