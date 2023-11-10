import Expressions.ArithmeticExpression;
import Expressions.Factory.MinimalExpressionFactory;

/**
 * Main class to test the expression factory
 */
public class Main {
    public static void main(String[] args) {
        // Create the factory
        MinimalExpressionFactory factory = new MinimalExpressionFactory();
        // Create an expression
        ArithmeticExpression expression = factory.createExpressionFromString("(1x+2y)*3");
        System.out.println(expression.evaluate());
        System.out.println(factory.simplify(expression));
    }
}