package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextLeaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractParser {

    private static final Logger logger = LogManager.getLogger(SymbolParser.class);

    @Override
    public TextComponent parse(String data) {
        if (data == null || data.length() != 1) {
            logger.warn("Некорректный символ: '{}'", data);
            throw new IllegalArgumentException("Ожидался одиночный символ, получено: " + data);
        }

        char ch = data.charAt(0);
        logger.debug("Создание TextLeaf для символа: '{}'", ch);
        return new TextLeaf(ch, ComponentType.SYMBOL);
    }
}
