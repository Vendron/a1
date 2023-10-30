package Expressions;

public class Constant extends ArithmeticExpression {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
