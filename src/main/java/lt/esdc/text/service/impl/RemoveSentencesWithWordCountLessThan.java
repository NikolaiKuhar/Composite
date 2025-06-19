package lt.esdc.text.service.impl;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.service.TextService;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class RemoveSentencesWithWordCountLessThan implements TextService<TextComponent> {

    private static final Logger logger = LogManager.getLogger(RemoveSentencesWithWordCountLessThan.class);
    private static final Pattern WORD_PATTERN = Pattern.compile(TextConstants.REGEX_WORD);

    private final int limit;

    public RemoveSentencesWithWordCountLessThan(int limit) {
        this.limit = limit;
    }

    @Override
    public TextComponent execute(TextComponent text) {
        logger.info("Удаление предложений с количеством слов меньше {}", limit);
        for (TextComponent paragraph : text.getChildren()) {
            paragraph.getChildren().removeIf(sentence ->
                    sentence.getChildren().stream()
                            .filter(lexeme -> WORD_PATTERN.matcher(lexeme.toString()).find())
                            .count() < limit
            );
        }
        return text;
    }
}
