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

    public HashMap<String, Double> getVariableInformation() {
        HashMap<String, Double> variableInformation = new HashMap<>();

        HashMap<String, Double> leftInfo = this.left.getVariableInformation();
        HashMap<String, Double> rightInfo = this.right.getVariableInformation();

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" - ").append(right.toString());
        return sb.toString();
    }

}
