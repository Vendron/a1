package Expressions.Operations;

import Expressions.ArithmeticExpression;
import java.util.HashMap;

public class Multiplication extends ArithmeticExpression {
    private final ArithmeticExpression left, right;

    public Multiplication(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * This method is used to get the variable information in a HashMap
     * @return The variable information in a HashMap
     */
    public HashMap<String, Double> simplify() {
        HashMap<String, Double> variableInformation = new HashMap<>();

        HashMap<String, Double> leftInfo = this.left.simplify();
        HashMap<String, Double> rightInfo = this.right.simplify();

        Double constant = leftInfo.containsKey("constant") && rightInfo.containsKey("constant")
                ? leftInfo.get("constant") * rightInfo.get("constant")
                : 0.0;
        variableInformation.put("constant", constant);

        // Merge the constant values of the left and right expressions
        for (String varName : leftInfo.keySet()) {
            if (varName.equals("constant")) {
                continue;
            }
            variableInformation.put(varName, leftInfo.get(varName) * rightInfo.get("constant"));
        }

        for (String varName : rightInfo.keySet()) {
            if (varName.equals("constant")) {
                continue;
            }
            variableInformation.put(varName, rightInfo.get(varName) * leftInfo.get("constant"));
        }

        for (String varName : leftInfo.keySet()) {
            if (varName.equals("constant")) {
                continue;
            }
            for (String otherVarName : rightInfo.keySet()) {
                if (otherVarName.equals("constant")) {
                    continue;
                }
                // Combine variable names
                String combinedVarName = varName + otherVarName;
                double combinedCoefficient = leftInfo.get(varName) * rightInfo.get(otherVarName);

                variableInformation.put(combinedVarName, combinedCoefficient);
            }
        }
        return variableInformation;
    }

    /**
     * This method is used to evaluate the expression
     * @return The value of the expression
     */
    @Override
    public double evaluate() {
        return left.evaluate() * right.evaluate();
    }

    /**
     * This method is used to convert the expression to a string
     * @return The string representation of the expression
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" * ").append(right.toString());
        return sb.toString();
    }

}