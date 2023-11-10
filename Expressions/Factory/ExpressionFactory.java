package Expressions.Factory;

import Expressions.ArithmeticExpression;

/*
 * Interface for the expression factory
 */
public interface ExpressionFactory {
    ArithmeticExpression createExpression(String expression);
}