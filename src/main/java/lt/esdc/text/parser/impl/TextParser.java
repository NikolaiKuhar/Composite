package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends AbstractParser {

    private static final Logger logger = LogManager.getLogger(TextParser.class);

    @Override
    public TextComponent parse(String data) {
        logger.info("Парсинг текста на абзацы");
        TextComposite text = new TextComposite(ComponentType.TEXT);

        String[] paragraphs = data.strip().split(TextConstants.PARAGRAPH_DELIMITER);
        for (String paragraph : paragraphs) {
            logger.debug("Обрабатывается абзац: {}", paragraph);
            TextComponent paragraphComponent = next.parse(paragraph.strip());
            text.add(paragraphComponent);
        }

        return text;
    }
}
