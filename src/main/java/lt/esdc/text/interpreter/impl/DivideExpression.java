package lt.esdc.text.interpreter.impl;

import lt.esdc.text.interpreter.ExpressionInterpreter;

public class DivideExpression implements ExpressionInterpreter {

    private final ExpressionInterpreter left;
    private final ExpressionInterpreter right;

    public DivideExpression(ExpressionInterpreter left, ExpressionInterpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret() {
        double rightValue = right.interpret();
        if (rightValue == 0.0) {
            throw new ArithmeticException("Деление на ноль запрещено");
        }
        return left.interpret() / rightValue;
    }
}
