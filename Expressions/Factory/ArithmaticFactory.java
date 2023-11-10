package Expressions.Factory;


import Expressions.ArithmeticExpression;
import Expressions.Operations.Addition;
import Expressions.Operations.Division;
import Expressions.Operations.Exponentiation;
import Expressions.Operations.Multiplication;
import Expressions.Operations.Subtraction;

public class ArithmaticFactory extends AbstractFactory {

    public ArithmaticFactory() {
        expressionMap.put("Addition", (left, right) -> new Addition(left, right));
        expressionMap.put("Subtraction", (left, right) -> new Subtraction(left, right));
        expressionMap.put("Multiplication", (left, right) -> new Multiplication(left, right));
        expressionMap.put("Division", (left, right) -> new Division(left, right));
    }

    static public Addition createAddition(ArithmeticExpression left, ArithmeticExpression right) {
        return new Addition(left, right);
    }

    static public Subtraction createSubtraction(ArithmeticExpression left, ArithmeticExpression right) {
        return new Subtraction(left, right);
    }

    static public Multiplication createMultiplication(ArithmeticExpression left, ArithmeticExpression right) {
        return new Multiplication(left, right);
    }

    static public Division createDivision(ArithmeticExpression left, ArithmeticExpression right) {
        return new Division(left, right);
    }

    static public Exponentiation createExponentiation(ArithmeticExpression left, ArithmeticExpression right) {
        return new Exponentiation(left, right);
    }
}
