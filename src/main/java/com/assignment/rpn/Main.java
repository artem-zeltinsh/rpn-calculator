package com.assignment.rpn;

import com.assignment.rpn.calculator.Calculator;
import com.assignment.rpn.calculator.CalculatorException;

import java.util.Scanner;
import java.util.Set;

import static com.assignment.rpn.operator.Operators.*;
import static java.lang.System.out;

/**
 * Command-line interface for RPN calculator.
 */
public class Main {

    private final static Set<String> exitCommands = Set.of("quit", "q", "exit");

    public static void main(String[] args) {
        printBanner();

        Calculator calculator = new Calculator(
                Set.of(ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT, UNDO, CLEAR));

        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            final String nextLine = input.nextLine().trim();
            if (exitCommands.contains(nextLine)) {
                break;
            } else if (!nextLine.isBlank()) {
                try {
                    out.println("stack: " + calculator.interpret(nextLine));
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
        out.println(" Enter `quit` or`exit` commands to finish the session.");
        out.println();
    }
}
