package Expressions.Factory;

import java.util.HashMap;
import java.util.function.BiFunction;

import Expressions.ArithmeticExpression;

public abstract class AbstractFactory implements ExpressionFactory {
    // Map of expressions to functions that create them
    protected static HashMap<String, BiFunction<ArithmeticExpression, ArithmeticExpression, ArithmeticExpression>> expressionMap = new HashMap<>();

    /*
     * Method to create an expression
     * 
     * @param expression: the expression to create
     * 
     * @return: the expression
     */
    @Override
    public ArithmeticExpression createExpression(String expression, ArithmeticExpression left, ArithmeticExpression right) {
        return expressionMap.get(expression).apply(left, right);
    }
}
