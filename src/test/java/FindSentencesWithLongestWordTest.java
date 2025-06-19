import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import lt.esdc.text.component.ComponentType;
import lt.esdc.text.service.impl.FindSentencesWithLongestWord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindSentencesWithLongestWordTest {

    @Test
    void testFindLongestWordSentence() {
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        sentence1.add(new TextLeaf('a', ComponentType.SYMBOL));
        sentence1.add(new TextLeaf('b', ComponentType.SYMBOL));
        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        sentence2.add(new TextLeaf('l', ComponentType.SYMBOL));
        sentence2.add(new TextLeaf('o', ComponentType.SYMBOL));
        sentence2.add(new TextLeaf('n', ComponentType.SYMBOL));
        sentence2.add(new TextLeaf('g', ComponentType.SYMBOL));
        paragraph.add(sentence1);
        paragraph.add(sentence2);
        text.add(paragraph);

        FindSentencesWithLongestWord finder = new FindSentencesWithLongestWord();
        TextComponent result = (TextComponent) finder.execute(text);
        assertEquals(sentence2, result);
    }
}
