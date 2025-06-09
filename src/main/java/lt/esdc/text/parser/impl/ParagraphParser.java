package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractParser {

    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);

    @Override
    public TextComponent parse(String paragraph) {
        logger.info("Парсинг абзаца на предложения");
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);

        String[] sentences = paragraph.strip().split(TextConstants.SENTENCE_DELIMITER);
        for (String sentence : sentences) {
            logger.debug("Предложение: {}", sentence);
            TextComponent sentenceComponent = next.parse(sentence.strip());
            paragraphComposite.add(sentenceComponent);
        }

        return paragraphComposite;
    }
}
