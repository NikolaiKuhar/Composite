import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.parser.Parser;
import lt.esdc.text.parser.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserChainIntegrationTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new TextParser();
        Parser paragraphParser = new ParagraphParser();
        Parser sentenceParser = new SentenceParser();
        Parser lexemeParser = new LexemeParser();
        Parser symbolParser = new SymbolParser();

        parser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    @Test
    void shouldParseSimpleTextCorrectly() {
        String text = "Hello world.\n\nTest sentence!";
        TextComponent result = parser.parse(text);

        assertEquals(ComponentType.TEXT, result.getType());
        assertEquals(2, result.getChildren().size()); // 2 paragraphs
    }
}
