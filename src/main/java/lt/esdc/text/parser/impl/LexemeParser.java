package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractParser {

    private static final Logger logger = LogManager.getLogger(LexemeParser.class);

    @Override
    public TextComponent parse(String lexeme) {
        logger.info("Парсинг лексемы: {}", lexeme);
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);

        if (lexeme == null || lexeme.isBlank()) {
            logger.warn("Пустая лексема пропущена");
            return lexemeComposite;
        }

        for (char ch : lexeme.toCharArray()) {
            TextComponent symbol = next.parse(Character.toString(ch));
            lexemeComposite.add(symbol);
        }

        return lexemeComposite;
    }
}
