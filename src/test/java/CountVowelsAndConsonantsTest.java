import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import lt.esdc.text.component.ComponentType;
import lt.esdc.text.service.impl.CountVowelsAndConsonants;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CountVowelsAndConsonantsTest {

    @Test
    void testVowelConsonantCount() {
        TextComposite text = new TextComposite(ComponentType.TEXT);
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        sentence.add(new TextLeaf('a', ComponentType.SYMBOL));
        sentence.add(new TextLeaf('b', ComponentType.SYMBOL));
        paragraph.add(sentence);
        text.add(paragraph);

        CountVowelsAndConsonants counter = new CountVowelsAndConsonants();
        Map<TextComponent, int[]> result = counter.execute(text);
        int[] counts = result.get(sentence);

        assertEquals(1, counts[0]); // vowels
        assertEquals(1, counts[1]); // consonants
    }
}
