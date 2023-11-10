package Expressions.Operations;

import Expressions.ArithmeticExpression;
import java.util.HashMap;

public class Multiplication extends ArithmeticExpression {
    private final ArithmeticExpression left, right;

    public Multiplication(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }
    
    // This won't work if you have something like (x + 1) * (x + 2)
    /*
     * This method is used to get the variable information in a HashMap
     */
    public HashMap<String, Double> getVariableInformation() {
        HashMap<String, Double> variableInformation = new HashMap<>();

        HashMap<String, Double> leftInfo = this.left.getVariableInformation();
        HashMap<String, Double> rightInfo = this.right.getVariableInformation();

        for (String varName : leftInfo.keySet()) {
            double leftCoefficient = leftInfo.get(varName);
            for (String otherVarName : rightInfo.keySet()) {
                double rightCoefficient = rightInfo.get(otherVarName);
    
                // Combine variable names
                String combinedVarName = varName + otherVarName; // This won't work if you have something like (x + 1) * (x + 2)
                double combinedCoefficient = leftCoefficient * rightCoefficient;
    
                // Update the map with the new combined variable and coefficient
                variableInformation.merge(combinedVarName, combinedCoefficient, Double::sum);
            }
        }

        return variableInformation;
    }

    @Override
    public double evaluate() {
        return left.evaluate() * right.evaluate();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" * ").append(right.toString());
        return sb.toString();
    }

}
