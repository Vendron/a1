package Expressions.Operations;

import Expressions.ArithmeticExpression;
import java.util.HashMap;

public class Division extends ArithmeticExpression {
    private final ArithmeticExpression left, right;

    public Division(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        //print out the value 
        System.out.println("Division: " + left.evaluate() + " / " + right.evaluate());
        return left.evaluate() / right.evaluate();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" / ").append(right.toString());
        return sb.toString();
    }

    /**
     * Simplify division expression by dividing the left and right expressions in hashmap
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
                variableInformation.put(varName, variableInformation.get(varName) / rightInfo.get(varName));
            } else {
                variableInformation.put(varName, rightInfo.get(varName));
            }
        }
        return variableInformation;
    }


}