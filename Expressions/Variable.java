package Expressions;

public class Variable extends ArithmeticExpression {
    private double coefficient;
    private String varName;

    public Variable(double coefficient, String varName) {
        this.coefficient = coefficient;
        this.varName = varName;
    }

    @Override
    public double evaluate() {
        // Since we don't know the actual value of the variable,
        // return the coefficient (you might adjust this depending on your use-case)
        return coefficient;
    }

    public String getVarName() {
        return varName;
    }

    public double getCoefficient() {
        return coefficient;
    }

    // Basic arithmetic operations:
    public ArithmeticExpression multiply(ArithmeticExpression other) {
        if (other instanceof Variable) {
            Variable otherVar = (Variable) other;
            if (this.varName.equals(otherVar.varName)) {
                return new Variable(this.coefficient * otherVar.coefficient, this.varName + "^2");
            } else {
                return new Variable(this.coefficient * otherVar.coefficient, this.varName + otherVar.varName);
            }
        } else if (other instanceof Constant) {
            double otherValue = other.evaluate();
            return new Variable(this.coefficient * otherValue, this.varName);
        }
        throw new IllegalArgumentException("Unsupported multiplication with type: " + other.getClass().getSimpleName());
    }

    public ArithmeticExpression add(ArithmeticExpression other) {
        if (other instanceof Variable) {
            Variable otherVar = (Variable) other;
            if (this.varName.equals(otherVar.varName)) {
                return new Variable(this.coefficient + otherVar.coefficient, this.varName);
            }
        } else if (other instanceof Constant) {
            double otherValue = other.evaluate();
            return new Variable(this.coefficient + otherValue, this.varName);
        }
        throw new IllegalArgumentException("Unsupported addition with type: " + other.getClass().getSimpleName());
    }
    
    public ArithmeticExpression subtract(ArithmeticExpression other) {
        if (other instanceof Variable) {
            Variable otherVar = (Variable) other;
            if (this.varName.equals(otherVar.varName)) {
                return new Variable(this.coefficient - otherVar.coefficient, this.varName);
            }
        } else if (other instanceof Constant) {
            double otherValue = other.evaluate();
            return new Variable(this.coefficient - otherValue, this.varName);
        }
        throw new IllegalArgumentException("Unsupported subtraction with type: " + other.getClass().getSimpleName());
    }
    
    public ArithmeticExpression divide(ArithmeticExpression other) {
        if (other.evaluate() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        if (other instanceof Variable) {
            Variable otherVar = (Variable) other;
            if (this.varName.equals(otherVar.varName)) {
                return new Constant(this.coefficient / otherVar.coefficient);
            } else {
                return new Variable(this.coefficient / otherVar.coefficient, this.varName + "/" + otherVar.varName);
            }
        } else if (other instanceof Constant) {
            double otherValue = other.evaluate();
            return new Variable(this.coefficient / otherValue, this.varName);
        }
        throw new IllegalArgumentException("Unsupported division with type: " + other.getClass().getSimpleName());
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coefficient != 1.0) { // Don't display coefficient if it's 1
            sb.append(coefficient);
        }
        sb.append(varName);
        return sb.toString();
    }

}
