package Expressions.Factory;

import Expressions.ArithmeticExpression;

public interface ExpressionFactory {
    ArithmeticExpression createExpression(String expression);
}
