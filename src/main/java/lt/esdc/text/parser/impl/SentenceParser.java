package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractParser {

    private static final Logger logger = LogManager.getLogger(SentenceParser.class);

    @Override
    public TextComponent parse(String sentence) {
        logger.info("Парсинг предложения на лексемы");
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);

        String[] lexemes = sentence.strip().split(TextConstants.LEXEME_DELIMITER);
        for (String lexeme : lexemes) {
            logger.debug("Лексема: {}", lexeme);
            TextComponent lexemeComponent = next.parse(lexeme);
            sentenceComposite.add(lexemeComponent);
        }

        return sentenceComposite;
    }
}
