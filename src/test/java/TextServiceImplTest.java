import lt.esdc.text.component.TextComponent;
import lt.esdc.text.parser.Parser;
import lt.esdc.text.parser.impl.*;
import lt.esdc.text.service.TextService;
import lt.esdc.text.service.impl.TextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {

    private TextService service;
    private TextComponent parsedText;

    @BeforeEach
    void setUp() {
        service = new TextServiceImpl();

        Parser textParser = new TextParser();
        Parser paragraphParser = new ParagraphParser();
        Parser sentenceParser = new SentenceParser();
        Parser lexemeParser = new LexemeParser();
        Parser symbolParser = new SymbolParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);

        String input = """
                This is a sentence. This is another.
                                
                Yet another paragraph with the longestwordeverwritten!
                """;

        parsedText = textParser.parse(input);
    }

    @Test
    void testSortParagraphsBySentenceCount() {
        List<TextComponent> sorted = service.sortParagraphsBySentenceCount(parsedText);
        assertEquals(2, sorted.size());
        assertTrue(sorted.get(0).getChildren().size() <= sorted.get(1).getChildren().size());
    }

    @Test
    void testFindSentencesWithLongestWord() {
        List<TextComponent> result = service.findSentencesWithLongestWord(parsedText);
        assertFalse(result.isEmpty());
        String sentenceStr = result.get(0).toString();
        assertTrue(sentenceStr.contains("longestwordeverwritten"));
    }

    @Test
    void testRemoveSentencesWithWordCountLessThan() {
        int before = countSentences(parsedText);
        service.removeSentencesWithWordCountLessThan(parsedText, 5);
        int after = countSentences(parsedText);
        assertTrue(after < before);
    }

    @Test
    void testFindAllRepeatedWords() {
        Map<String, Long> repeated = service.findAllRepeatedWords(parsedText);
        assertTrue(repeated.containsKey("this"));
        assertTrue(repeated.get("this") >= 2);
    }

    @Test
    void testCountVowelsAndConsonants() {
        Map<TextComponent, int[]> result = service.countVowelsAndConsonants(parsedText);
        assertFalse(result.isEmpty());
        for (int[] counts : result.values()) {
            int vowels = counts[0];
            int consonants = counts[1];
            assertTrue(vowels >= 0 && consonants >= 0);
        }
    }

    private int countSentences(TextComponent text) {
        return text.getChildren().stream()
                .mapToInt(p -> p.getChildren().size())
                .sum();
    }
}
