import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.ComponentType;
import lt.esdc.text.service.impl.SortParagraphsBySentenceCount;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SortParagraphsBySentenceCountTest {

    @Test
    void testSorting() {
        TextComposite text = new TextComposite(ComponentType.TEXT);

        TextComposite p1 = new TextComposite(ComponentType.PARAGRAPH);
        p1.add(new TextComposite(ComponentType.SENTENCE));
        text.add(p1);

        TextComposite p2 = new TextComposite(ComponentType.PARAGRAPH);
        p2.add(new TextComposite(ComponentType.SENTENCE));
        p2.add(new TextComposite(ComponentType.SENTENCE));
        text.add(p2);

        SortParagraphsBySentenceCount sorter = new SortParagraphsBySentenceCount();
        List<TextComponent> sorted = sorter.execute(text);

        assertEquals(p1, sorted.get(0));
        assertEquals(p2, sorted.get(1));
    }
}
