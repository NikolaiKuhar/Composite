package lt.esdc.text.service.impl;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.service.TextService;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FindSentencesWithLongestWord implements TextService<List<TextComponent>> {

    private static final Logger logger = LogManager.getLogger(FindSentencesWithLongestWord.class);

    @Override
    public List<TextComponent> execute(TextComponent text) {
        logger.info("Поиск предложений с самым длинным словом");
        List<TextComponent> result = new ArrayList<>();
        int maxLength = 0;

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int localMax = sentence.getChildren().stream()
                        .mapToInt(lexeme -> lexeme.toString().replaceAll("[^" + TextConstants.REGEX_WORD + "]", "").length())
                        .max().orElse(0);

                if (localMax > maxLength) {
                    result.clear();
                    result.add(sentence);
                    maxLength = localMax;
                } else if (localMax == maxLength) {
                    result.add(sentence);
                }
            }
        }

        return result;
    }
}
