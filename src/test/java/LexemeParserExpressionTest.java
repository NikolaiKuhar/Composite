import lt.esdc.text.component.TextComponent;
import lt.esdc.text.parser.impl.LexemeParser;
import lt.esdc.text.parser.impl.SymbolParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexemeParserExpressionTest {

    private LexemeParser parser;

    @BeforeEach
    void init() {
        parser = new LexemeParser();
        parser.setNext(new SymbolParser());
    }

    @Test
    void testMathExpressionParsing() {
        TextComponent result = parser.parse("2+3");
        assertEquals("5", result.toString());
    }

    @Test
    void testNonExpressionLexeme() {
        TextComponent result = parser.parse("Hi!");
        assertEquals("Hi!", result.toString());
    }
}
