package Expressions;

import java.util.HashMap;

public class Constant extends ArithmeticExpression {
    private final Double value;

    public Constant(Double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return Double.valueOf(this.value);
    }

    public StringBuilder toStringBuilder() {
        return new StringBuilder(Double.toString(this.value));
    }

    @Override
    public String toString() {
        return this.toStringBuilder().toString();
    }

    // Simplify constant expression by returning the value in hashmap
    public HashMap<String, Double> simplify() {
        return new HashMap<String, Double>(
            new HashMap<String, Double>() {
                {
                    put("constant", value);
                }
            }
        );
    }

}
