import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {

    @Test
    void testAddAndGetChildren() {
        TextComposite composite = new TextComposite(ComponentType.LEXEME);
        TextLeaf symbol = new TextLeaf('a', ComponentType.SYMBOL);

        composite.add(symbol);

        List<TextComponent> children = composite.getChildren();
        assertEquals(1, children.size());
        assertEquals(symbol, children.get(0));
    }

    @Test
    void testToString() {
        TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
        lexeme.add(new TextLeaf('H', ComponentType.SYMBOL));
        lexeme.add(new TextLeaf('i', ComponentType.SYMBOL));

        assertEquals("Hi", lexeme.toString());
    }

    @Test
    void testTypeEquality() {
        TextComposite c1 = new TextComposite(ComponentType.SENTENCE);
        TextComposite c2 = new TextComposite(ComponentType.SENTENCE);
        assertEquals(c1, c2);
    }
}
