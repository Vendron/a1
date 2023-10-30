import Expressions.ArithmeticExpression;
import Expressions.Factory.ExpressionFactory;
import Expressions.Factory.MinimalExpressionFactory;

public class Main {
    public static void main(String[] args) {
        ExpressionFactory factory = new MinimalExpressionFactory();
        ArithmeticExpression expression = factory.createExpression("(4x*3)-((24/4)*15)");
        System.out.println(expression.evaluate());
    }
}