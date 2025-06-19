import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import lt.esdc.text.component.ComponentType;
import lt.esdc.text.service.impl.RemoveSentencesWithWordCountLessThan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RemoveSentencesWithWordCountLessThanTest {

    @Test
    void testRemoveShortSentences() {
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite shortSentence = new TextComposite(ComponentType.SENTENCE);
        shortSentence.add(new TextLeaf('A', ComponentType.SYMBOL));
        TextComposite longSentence = new TextComposite(ComponentType.SENTENCE);
        longSentence.add(new TextLeaf('W', ComponentType.SYMBOL));
        longSentence.add(new TextLeaf('o', ComponentType.SYMBOL));
        longSentence.add(new TextLeaf('r', ComponentType.SYMBOL));
        paragraph.add(shortSentence);
        paragraph.add(longSentence);
        text.add(paragraph);

        RemoveSentencesWithWordCountLessThan remover = new RemoveSentencesWithWordCountLessThan(2);
        TextComponent result = remover.execute(text);
        assertTrue(result.toString().contains("Wor"));
        assertFalse(result.toString().contains("A"));
    }
}
