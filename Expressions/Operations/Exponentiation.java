package Expressions.Operations;

import Expressions.ArithmeticExpression;

public class Exponentiation extends ArithmeticExpression {
    private final ArithmeticExpression left, right;
    
        public Exponentiation(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
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
