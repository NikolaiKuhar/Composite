import lt.esdc.text.interpreter.ExpressionInterpreter;
import lt.esdc.text.interpreter.impl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionInterpreterTest {

    @Test
    void testNumberExpression() {
        ExpressionInterpreter number = new NumberExpression(7);
        assertEquals(7.0, number.interpret(), 0.0001);
    }

    @Test
    void testAddExpression() {
        ExpressionInterpreter add = new AddExpression(new NumberExpression(2), new NumberExpression(3));
        assertEquals(5.0, add.interpret(), 0.0001);
    }

    @Test
    void testSubtractExpression() {
        ExpressionInterpreter subtract = new SubtractExpression(new NumberExpression(10), new NumberExpression(4));
        assertEquals(6.0, subtract.interpret(), 0.0001);
    }

    @Test
    void testMultiplyExpression() {
        ExpressionInterpreter multiply = new MultiplyExpression(new NumberExpression(3), new NumberExpression(5));
        assertEquals(15.0, multiply.interpret(), 0.0001);
    }

    @Test
    void testDivideExpression() {
        ExpressionInterpreter divide = new DivideExpression(new NumberExpression(10), new NumberExpression(2));
        assertEquals(5.0, divide.interpret(), 0.0001);
    }

    @Test
    void testDivideByZeroThrows() {
        ExpressionInterpreter divide = new DivideExpression(new NumberExpression(5), new NumberExpression(0));
        assertThrows(ArithmeticException.class, divide::interpret);
    }
}
