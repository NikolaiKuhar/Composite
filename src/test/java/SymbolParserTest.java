import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.parser.impl.SymbolParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolParserTest {

    private final SymbolParser parser = new SymbolParser();

    @Test
    void testSymbolParsing() {
        TextComponent component = parser.parse("A");
        assertEquals("A", component.toString());
        assertEquals(ComponentType.SYMBOL, component.getType());
    }

    @Test
    void testInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("AB"));
    }
}
