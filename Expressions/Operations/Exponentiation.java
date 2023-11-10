package Expressions.Operations;

import Expressions.ArithmeticExpression;
import java.util.HashMap;

public class Exponentiation extends ArithmeticExpression {
    private final ArithmeticExpression left, right;
    
    public Exponentiation(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    public HashMap<String, Double> getVariableInformation() {
        HashMap<String, Double> variableInformation = new HashMap<>();

        HashMap<String, Double> leftInfo = this.left.getVariableInformation();
        HashMap<String, Double> rightInfo = this.right.getVariableInformation();

        for (String varName : leftInfo.keySet()) {
            variableInformation.put(varName, leftInfo.get(varName));
        }

        for (String varName : rightInfo.keySet()) {
            if (variableInformation.containsKey(varName)) {
                variableInformation.put(varName, variableInformation.get(varName) + rightInfo.get(varName));
            } else {
                variableInformation.put(varName, rightInfo.get(varName));
            }
        }
        return variableInformation;
    }

    @Override
    public double evaluate() {
        return Math.pow(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" ^ ").append(right.toString());
        return sb.toString();
    }

}
