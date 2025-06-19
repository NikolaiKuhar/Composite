package lt.esdc.text.service.impl;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.service.TextService;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.stream.Collectors;

public class FindAllRepeatedWords implements TextService<Map<String, Long>> {

    private static final Logger logger = LogManager.getLogger(FindAllRepeatedWords.class);

    @Override
    public Map<String, Long> execute(TextComponent text) {
        logger.info("Поиск повторяющихся слов");
        return text.getChildren().stream()
                .flatMap(p -> p.getChildren().stream()) // sentences
                .flatMap(s -> s.getChildren().stream()) // lexemes
                .map(l -> l.toString().replaceAll("[^" + TextConstants.REGEX_WORD + "]", "").toLowerCase())
                .filter(w -> !w.isEmpty())
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
