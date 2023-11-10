package Expressions.Factory;


import Expressions.ArithmeticExpression;
import Expressions.Operations.Addition;
import Expressions.Operations.Division;
import Expressions.Operations.Exponentiation;
import Expressions.Operations.Multiplication;
import Expressions.Operations.Subtraction;

public class ArithmaticFactory extends AbstractFactory {

    /**
     * Constructor for the ArithmaticFactory
     * Creates a map of the expression types and the expression
     * @return expressionMap The map of the expression types and the expression
     */
    public ArithmaticFactory() {
        expressionMap.put("Addition", (left, right) -> new Addition(left, right));
        expressionMap.put("Subtraction", (left, right) -> new Subtraction(left, right));
        expressionMap.put("Multiplication", (left, right) -> new Multiplication(left, right));
        expressionMap.put("Division", (left, right) -> new Division(left, right));
    }

    /**
     * Creates an addition expression
     * @param left The left expression
     * @param right The right expression
     * @return Addition The addition expression
     */
    static public Addition createAddition(ArithmeticExpression left, ArithmeticExpression right) {
        return new Addition(left, right);
    }

    /**
     * Creates a subtraction expression
     * @param left The left expression
     * @param right The right expression
     * @return Subtraction The subtraction expression
     */
    static public Subtraction createSubtraction(ArithmeticExpression left, ArithmeticExpression right) {
        return new Subtraction(left, right);
    }

    /**
     * Creates a multiplication expression
     * @param left The left expression
     * @param right The right expression
     * @return Multiplication The multiplication expression
     */
    static public Multiplication createMultiplication(ArithmeticExpression left, ArithmeticExpression right) {
        return new Multiplication(left, right);
    }

    /**
     * Creates a division expression
     * @param left
     * @param right
     * @return Division The division expression
     */
    static public Division createDivision(ArithmeticExpression left, ArithmeticExpression right) {
        return new Division(left, right);
    }

    /**
     * Creates an exponentiation expression
     * @param left
     * @param right
     * @return Exponentiation The exponentiation expression
     */
    static public Exponentiation createExponentiation(ArithmeticExpression left, ArithmeticExpression right) {
        return new Exponentiation(left, right);
    }
}
