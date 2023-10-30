package Expressions.Operations;

import Expressions.ArithmeticExpression;
import Expressions.Variable;

//addtion of left and right expressions
public class Subtraction extends ArithmeticExpression {
    private final ArithmeticExpression left;
    private final ArithmeticExpression right;

    public Subtraction(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
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
