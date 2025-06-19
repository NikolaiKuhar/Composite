package lt.esdc.text.interpreter.impl;

import lt.esdc.text.interpreter.ExpressionInterpreter;

public class MultiplyExpression implements ExpressionInterpreter {

    private final ExpressionInterpreter left;
    private final ExpressionInterpreter right;

    public MultiplyExpression(ExpressionInterpreter left, ExpressionInterpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret() {
        return left.interpret() * right.interpret();
    }
}
