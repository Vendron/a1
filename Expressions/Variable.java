package Expressions;

import java.util.HashMap;

public class Variable extends ArithmeticExpression {
    private double coefficient;
    private String varName;

    /**
     * Creates a variable with a coefficient of 1
     * @param varName the name of the variable
     */
    public Variable(double coefficient, String varName) {
        this.coefficient = coefficient;
        this.varName = varName;
    }

    /**
     * evaluates the variable to its value
     */
    @Override
    public double evaluate() {
        return coefficient;
    }

    /**
     * Simplifies the variable
     * @return: the simplified variable
     */
    public String getVarName() {
        return varName;
    }

    /**
     * Returns the variable information of the variable
     */
    public Double getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coefficient != 1.0) { // Don't display coefficient if it's 1
            sb.append(coefficient);
        }
        sb.append(varName);
        return sb.toString();
    }

    /**
     * Sums the coefficients of the same variable
     * @param variableInformation the variable information to add to
     * @param varName the name of the variable
     * @param coefficient the coefficient of the variable
     * @return: the updated variable information
     */
    public HashMap<String, Double> simplify() {
        return new HashMap<String, Double>() {
            {
                put(varName, coefficient);
            }
        };
    }

}
