import Expressions.ArithmeticExpression;
import Expressions.Factory.MinimalExpressionFactory;

public class Main {
    public static void main(String[] args) {
        MinimalExpressionFactory factory = new MinimalExpressionFactory();
        ArithmeticExpression expression = factory.createExpressionFromString("(4x*3)-((24/4)*15)");
        System.out.println(expression.evaluate());
        System.out.println(factory.simplify(expression));
    }
}