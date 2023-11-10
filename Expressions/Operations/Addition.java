package Expressions.Operations;

import Expressions.ArithmeticExpression;
import Expressions.Variable;
import java.util.HashMap;

//addtion of left and right expressions
public class Addition extends ArithmeticExpression {
    private final ArithmeticExpression left, right;

    public Addition(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Simplify addition expression by adding the left and right expressions in hashmap
     * @return: a hashmap of the variable information
     */
    public HashMap<String, Double> simplify() {
        //Initialize the hashmap
        HashMap<String, Double> variableInformation = new HashMap<>();

        // Get the variable information from the left and right expressions
        HashMap<String, Double> leftInfo = this.left.simplify();
        HashMap<String, Double> rightInfo = this.right.simplify();

        // Merge the two hashmaps, adding the values of the same keys
        //Left info
        for (String varName : leftInfo.keySet()) {
            variableInformation.put(varName, leftInfo.get(varName));
        }
        //Right info
        for (String varName : rightInfo.keySet()) {
            if (variableInformation.containsKey(varName)) {
                variableInformation.put(varName, variableInformation.get(varName) + rightInfo.get(varName));
            } else {
                variableInformation.put(varName, rightInfo.get(varName));
            }
        }
        return variableInformation;
    }

    /**
     * @brief: evaluate the expression
     * @return The value of the expression
     */
    @Override
    public double evaluate() {
        if (left instanceof Variable && right instanceof Variable) {
            Variable leftVar = (Variable) left;
            Variable rightVar = (Variable) right;
            if (!leftVar.getVarName().equals(rightVar.getVarName())) {
                throw new IllegalArgumentException("Cannot add variables with different names: " + leftVar.getVarName()
                        + " and " + rightVar.getVarName());
            }
            return leftVar.getCoefficient() + rightVar.getCoefficient();
        }
        return left.evaluate() + right.evaluate();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString()).append(" + ").append(right.toString());
        return sb.toString();
    }

}
