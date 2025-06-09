import lt.esdc.text.exception.TextException;
import lt.esdc.text.interpreter.ExpressionInterpreter;
import lt.esdc.text.interpreter.impl.MathExpressionInterpreter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathExpressionInterpreterTest {

    private ExpressionInterpreter interpreter;

    @BeforeEach
    void setUp() {
        interpreter = new MathExpressionInterpreter();
    }

    @Test
    void testSimpleAddition() throws TextException {
        assertEquals(5, interpreter.interpret("2 + 3"));
    }

    @Test
    void testComplexExpression() throws TextException {
        String expr = "5 * (2 + 3) - 4 / 2";
        assertEquals(23, interpreter.interpret(expr));
    }

    @Test
    void testWithWhitespaces() throws TextException {
        assertEquals(7, interpreter.interpret("  1 + 2 * 3 "));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(TextException.class, () -> interpreter.interpret("5 / (3 - 3)"));
    }

    @Test
    void testMalformedExpression() {
        assertThrows(TextException.class, () -> interpreter.interpret("2 + * 5"));
    }

    @Test
    void testSingleNumber() throws TextException {
        assertEquals(42, interpreter.interpret("42"));
    }
}
