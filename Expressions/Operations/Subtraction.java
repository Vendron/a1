package Expressions.Operations;

import java.util.HashMap;

import Expressions.ArithmeticExpression;
import Expressions.Variable;

//addtion of left and right expressions
public class Subtraction extends ArithmeticExpression {
    private final ArithmeticExpression left, right;
    
    public Subtraction(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Simplify subtraction expression by subtracting the left and right expressions in hashmap
     * @return Variable information in a HashMap
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
                variableInformation.put(varName, variableInformation.get(varName) - rightInfo.get(varName));
            } else {
                variableInformation.put(varName, -rightInfo.get(varName));
            }
        }
        return variableInformation;
    }

    /**
     * @return The value of the expression
     */
    @Override
    public double evaluate() {
        if (left instanceof Variable && right instanceof Variable) {
            Variable leftVar = (Variable) left;
            Variable rightVar = (Variable) right;
            if (!leftVar.getVarName().equals(rightVar.getVarName())) {
                throw new IllegalArgumentException("Cannot subtract variables with different names: " + leftVar.getVarName()
                        + " and " + rightVar.getVarName());
            }
            return leftVar.getCoefficient() - rightVar.getCoefficient();
        }
        return left.evaluate() - right.evaluate();
    }

    /**
     * @return The string representation of the expression
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" - ").append(right.toString());
        return sb.toString();
    }

}
