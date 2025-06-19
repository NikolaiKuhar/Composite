import lt.esdc.text.interpreter.ExpressionParser;
import lt.esdc.text.interpreter.ExpressionInterpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionParserTest {

    private final ExpressionParser parser = new ExpressionParser();

    @Test
    void testSimpleAddition() {
        ExpressionInterpreter expr = parser.parse("2 + 3");
        assertEquals(5.0, expr.interpret(), 0.0001);
    }

    @Test
    void testOperatorPrecedence() {
        ExpressionInterpreter expr = parser.parse("2 + 3 * 4");
        assertEquals(14.0, expr.interpret(), 0.0001);
    }

    @Test
    void testParentheses() {
        ExpressionInterpreter expr = parser.parse("(2 + 3) * 4");
        assertEquals(20.0, expr.interpret(), 0.0001);
    }

    @Test
    void testNegativeNumbers() {
        ExpressionInterpreter expr = parser.parse("-5 + 2");
        assertEquals(-3.0, expr.interpret(), 0.0001);
    }

    @Test
    void testDivision() {
        ExpressionInterpreter expr = parser.parse("10 / 2");
        assertEquals(5.0, expr.interpret(), 0.0001);
    }

    @Test
    void testComplexExpression() {
        ExpressionInterpreter expr = parser.parse("10 + 2 * (6 - 3)");
        assertEquals(16.0, expr.interpret(), 0.0001);
    }
}
