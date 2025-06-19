import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import lt.esdc.text.component.ComponentType;
import lt.esdc.text.service.impl.FindAllRepeatedWords;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class FindAllRepeatedWordsTest {

    @Test
    void testRepeatedWords() {
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        sentence.add(new TextLeaf('t', ComponentType.SYMBOL));
        sentence.add(new TextLeaf('e', ComponentType.SYMBOL));
        sentence.add(new TextLeaf('s', ComponentType.SYMBOL));
        sentence.add(new TextLeaf('t', ComponentType.SYMBOL));
        paragraph.add(sentence);
        paragraph.add(sentence);
        text.add(paragraph);

        FindAllRepeatedWords finder = new FindAllRepeatedWords();
        Map<String, Long> result = finder.execute(text);

        assertTrue(result.containsKey("test"));
        assertTrue(result.get("test") > 1);
    }
}
