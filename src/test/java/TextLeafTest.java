import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.impl.TextLeaf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextLeafTest {

    @Test
    void testToStringAndType() {
        TextLeaf leaf = new TextLeaf('A', ComponentType.SYMBOL);
        assertEquals("A", leaf.toString());
        assertEquals(ComponentType.SYMBOL, leaf.getType());
    }

    @Test
    void testEquality() {
        TextLeaf leaf1 = new TextLeaf('a', ComponentType.SYMBOL);
        TextLeaf leaf2 = new TextLeaf('a', ComponentType.SYMBOL);
        assertEquals(leaf1, leaf2);
        assertEquals(leaf1.hashCode(), leaf2.hashCode());
    }

    @Test
    void testUnsupportedChildrenOperations() {
        TextLeaf leaf = new TextLeaf('x', ComponentType.SYMBOL);
        assertThrows(UnsupportedOperationException.class, () -> leaf.add(null));
        assertThrows(UnsupportedOperationException.class, leaf::getChildren);
    }
}
