package Expressions.Operations;

import Expressions.ArithmeticExpression;
import java.util.HashMap;

public class Exponentiation extends ArithmeticExpression {
    private final ArithmeticExpression left, right;
    
    public Exponentiation(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * This method is used to get the variable information in a HashMap 
     * Cannot simplify a power expression so we just return the expression itself
     * @return The variable information in a HashMap
     */
    public HashMap<String, Double> simplify() {
        HashMap<String, Double> variableInformation = new HashMap<>();

        HashMap<String, Double> leftInfo = this.left.simplify();
        HashMap<String, Double> rightInfo = this.right.simplify();

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

    /**
     * @return The value of the expression
     */
    @Override
    public double evaluate() {
        return Math.pow(left.evaluate(), right.evaluate());
    }

    /**
     * @return The string representation of the expression
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" ^ ").append(right.toString());
        return sb.toString();
    }

}
