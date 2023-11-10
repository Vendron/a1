package Expressions.Factory;

import java.util.HashMap;

import Expressions.ArithmeticExpression;
import Expressions.Constant;
import Expressions.Variable;


public class MinimalExpressionFactory extends AbstractFactory {
    private HashMap<String, Double> variablesInfomation;

    public String simplify(ArithmeticExpression expression) {
        this.variablesInfomation = expression.simplify();
        return hashToString();
    }

    public ArithmeticExpression createExpressionFromString(String expression) {

        expression = expression.replaceAll("\\s", ""); // Remove any spaces
        System.out.println("Current Expression: " + expression); // Debugging line

        if (expression.charAt(0) == '(' && findMatchingBracket(expression, 0) == expression.length() - 1) {
            return createExpressionFromString(expression.substring(1, expression.length() - 1));
        }

        /*
         * Handle Brackets
         * We will replace the brackets with the expression inside them, and then
         * recursively call createExpressionFromString() on the new expression
         * @example: 9+((24/4)*15) -> 9+((6)*15) -> 9+((90)) -> 9+(90) -> 9+90
         */
        int openPos = expression.indexOf('(');
        while (openPos != -1) {
            int pos = findMatchingBracket(expression, openPos); // pos is for closing bracket
            if (pos == -1) {
                throw new IllegalArgumentException("Mismatched brackets in expression: " + expression);
            }
            String inside = expression.substring(openPos + 1, pos);

            // Debugging logs
            System.out.println("Opening bracket at: " + openPos + ", closing bracket at: " + pos);
            System.out.println("Expression inside brackets: " + inside);

            ArithmeticExpression insideExpr = createExpressionFromString(inside);
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

        // Handle Addition
        openPos = expression.indexOf("+");
        if (openPos != -1) {
            return ArithmaticFactory.createAddition (
                    createExpressionFromString(expression.substring(0, openPos)),
                    createExpressionFromString(expression.substring(openPos + 1)));
        }

        // Handle Subtraction (need to ensure it's not a negative number)
        openPos = expression.lastIndexOf("-");
        if (openPos > 0) {
            return ArithmaticFactory.createSubtraction(
                    createExpressionFromString(expression.substring(0, openPos)),
                    createExpressionFromString("-" + expression.substring(openPos + 1)));
        }

        // Handle Exponentiation
        openPos = expression.indexOf("^");
        if (openPos != -1) {
            return ArithmaticFactory.createExponentiation(
                    createExpressionFromString(expression.substring(0, openPos)),
                    createExpressionFromString(expression.substring(openPos + 1)));
        }

        // Handle Multiplication
        openPos = expression.indexOf("*");
        if (openPos != -1) {
            return ArithmaticFactory.createMultiplication(
                    createExpressionFromString(expression.substring(0, openPos)),
                    createExpressionFromString(expression.substring(openPos + 1)));
        }
        
        // Handle Division
        openPos = expression.indexOf("/");
        if (openPos != -1) {
            return ArithmaticFactory.createDivision(
                    createExpressionFromString(expression.substring(0, openPos)),
                    createExpressionFromString(expression.substring(openPos + 1)));
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

    /*
     * Finds the matching closing bracket for the opening bracket at startPos
     * @param expression The expression to search in
     * @param startPos The position of the opening bracket
     * @return The position of the matching closing bracket, or -1 if not found
     */
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
        return -1;
    }

    /*
     * This method is used to convert the hash map to a string
    */
    private String hashToString(){
        //print out the hashmap
        this.variablesInfomation.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

        //remove variabnles with 0.0
        this.variablesInfomation.entrySet().removeIf(entry -> entry.getValue() == 0.0);

        //change hashmap to string
        StringBuilder sb = new StringBuilder();
        for (String varName : this.variablesInfomation.keySet()) {
            if (varName.equals("constant")) {
                continue;
            }
            sb.append(this.variablesInfomation.get(varName)).append(varName).append(" + ");
        }

        //add constant
        sb.append(this.variablesInfomation.get("constant"));

        return sb.toString();
    }
}
