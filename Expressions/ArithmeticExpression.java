package Expressions;

import java.util.HashMap;

public abstract class ArithmeticExpression {
    protected ArithmeticExpression() {
    }
    /**
     * Returns the value of the expression
     * @return: the value of the expression
     */
    public abstract double evaluate();

    /**
     * Returns the variable information of the expression
     */
    public abstract HashMap<String, Double> simplify();

    // /**
    //  * Method to convert the expression to a string
    //  * @return: string representation of the expression
    //  */
    // public abstract StringBuilder toStringBuilder();

    /**
     * Method to convert the expression to a string
     * @return: string representation of the expression
     */
    public abstract String toString();
}
