import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.parser.Parser;
import lt.esdc.text.parser.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

    private Parser textParser;

    @BeforeEach
    void setUp() {
        textParser = new TextParser();
        Parser paragraphParser = new ParagraphParser();
        Parser sentenceParser = new SentenceParser();
        Parser lexemeParser = new LexemeParser();
        Parser symbolParser = new SymbolParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    @Test
    void testParseStructure() {
        String input = "Hello world.\n\nAnother paragraph!";
        TextComponent parsed = textParser.parse(input);

        assertEquals(ComponentType.TEXT, parsed.getType());
        assertEquals(2, parsed.getChildren().size(), "Ожидалось 2 абзаца");

        TextComponent firstParagraph = parsed.getChildren().get(0);
        assertEquals(ComponentType.PARAGRAPH, firstParagraph.getType());
        assertEquals(1, firstParagraph.getChildren().size(), "Первый абзац должен содержать 1 предложение");

        TextComponent firstSentence = firstParagraph.getChildren().get(0);
        assertEquals(ComponentType.SENTENCE, firstSentence.getType());
        assertTrue(firstSentence.getChildren().size() >= 2, "В предложении должно быть >= 2 лексем");
    }

    @Test
    void testEmptyInput() {
        String input = "";
        TextComponent parsed = textParser.parse(input);
        assertEquals(0, parsed.getChildren().size(), "Ожидалось 0 абзацев для пустого ввода");
    }
}
