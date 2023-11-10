package Expressions.Factory;

import java.util.HashMap;
import java.util.function.BiFunction;

import Expressions.ArithmeticExpression;

public abstract class AbstractFactory implements ExpressionFactory {
    // Map of expressions to functions that create them
    protected static HashMap<String, BiFunction<ArithmeticExpression, ArithmeticExpression, ArithmeticExpression>> expressionMap = new HashMap<>();

    /**
     * Creates an expression
     * @param expression The expression to create
     * @param left The left expression
     * @param right The right expression
     * @return The created expression
     */
    @Override
    public ArithmeticExpression createExpression(String expression, ArithmeticExpression left, ArithmeticExpression right) {
        return expressionMap.get(expression).apply(left, right);
    }
}
