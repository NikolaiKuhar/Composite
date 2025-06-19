import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextComponentEqualsHashTest {

    @Test
    void testLeafEqualityAndHash() {
        TextLeaf a = new TextLeaf('x', ComponentType.SYMBOL);
        TextLeaf b = new TextLeaf('x', ComponentType.SYMBOL);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testCompositeEqualityAndHash() {
        TextComposite c1 = new TextComposite(ComponentType.SENTENCE);
        TextComposite c2 = new TextComposite(ComponentType.SENTENCE);
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
