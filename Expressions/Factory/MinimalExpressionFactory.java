package Expressions.Factory;

import Expressions.ArithmeticExpression;
import Expressions.Constant;
import Expressions.Variable;
import Expressions.Operations.Addition;
import Expressions.Operations.Division;
import Expressions.Operations.Exponentiation;
import Expressions.Operations.Multiplication;
import Expressions.Operations.Subtraction;

public class MinimalExpressionFactory implements ExpressionFactory {

    @Override
    public ArithmeticExpression createExpression(String expression) {
        expression = expression.replaceAll("\\s", ""); // Remove any spaces
        System.out.println("Current Expression: " + expression); // Debugging line

        if (expression.charAt(0) == '(' && findMatchingBracket(expression, 0) == expression.length() - 1) {
            return createExpression(expression.substring(1, expression.length() - 1));
        }

        // Handle Brackets
        int openPos = expression.indexOf('(');
        while (openPos != -1) {
            int pos = findMatchingBracket(expression, openPos); // pos is for closing bracket
            if (pos == -1) {
                throw new IllegalArgumentException("Mismatched brackets in expression: " + expression);
            }
            String inside = expression.substring(openPos + 1, pos);

            // Debugging logs
            System.out.println("Opening bracket at: " + openPos);
            System.out.println("Matching closing bracket at: " + pos);
            System.out.println("Expression inside brackets: " + inside);

            ArithmeticExpression insideExpr = createExpression(inside);
            if (insideExpr instanceof Variable) {
                expression = expression.substring(0, openPos) + insideExpr.toString() + expression.substring(pos + 1);
            } else {
                String result = insideExpr.toString();
            expression = expression.substring(0, openPos) + result + expression.substring(pos + 1);

            }
            

            // Debugging log for the new expression after replacement
            System.out.println("Expression after bracket replacement: " + expression);

            openPos = expression.indexOf('('); // Find the next opening bracket for the next iteration
        }

        // Handle Exponentiation
        openPos = expression.indexOf("^");
        if (openPos != -1) {
            return new Exponentiation(
                    createExpression(expression.substring(0, openPos)),
                    createExpression(expression.substring(openPos + 1)));
        }

        // Handle Multiplication
        openPos = expression.indexOf("*");
        if (openPos != -1) {
            return new Multiplication(
                    createExpression(expression.substring(0, openPos)),
                    createExpression(expression.substring(openPos + 1)));
        }

        // Handle Division
        openPos = expression.indexOf("/");
        if (openPos != -1) {
            return new Division(
                    createExpression(expression.substring(0, openPos)),
                    createExpression(expression.substring(openPos + 1)));
        }

        // Handle Addition
        openPos = expression.indexOf("+");
        if (openPos != -1) {
            return new Addition(
                    createExpression(expression.substring(0, openPos)),
                    createExpression(expression.substring(openPos + 1)));
        }

        // Handle Subtraction (need to ensure it's not a negative number)
        openPos = expression.lastIndexOf("-");
        if (openPos > 0) {
            return new Subtraction(
                    createExpression(expression.substring(0, openPos)),
                    createExpression("-" + expression.substring(openPos + 1)));
        }

        // Check if it's a variable or a coefficient followed by a variable
        if (expression.matches("[-]?[0-9]*\\.?[0-9]*[a-zA-Z]+")) {
            int j = 0;
            while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.'
                    || expression.charAt(j) == '-')) {
                j++;
            }
            double coefficient = j > 0 ? Double.parseDouble(expression.substring(0, j)) : 1;
            String varName = expression.substring(j);
            return new Variable(coefficient, varName);
        }

        // Handle Negative Number
        expression = expression.trim();
        if (!expression.matches("[+-]?\\d*(\\.\\d+)?")) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        // If we reached here, it's a number
        return new Constant(Double.parseDouble(expression));

    }

    private int findMatchingBracket(String expression, int startPos) {
        int depth = 0;
        for (int i = startPos; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                depth++;
            } else if (expression.charAt(i) == ')') {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        return -1; // No matching bracket found
    }
}
