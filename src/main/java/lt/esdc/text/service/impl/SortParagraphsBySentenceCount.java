package lt.esdc.text.service.impl;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortParagraphsBySentenceCount implements TextService<List<TextComponent>> {

    private static final Logger logger = LogManager.getLogger(SortParagraphsBySentenceCount.class);

    @Override
    public List<TextComponent> execute(TextComponent text) {
        logger.info("Сортировка абзацев по количеству предложений");
        return text.getChildren().stream()
                .sorted(Comparator.comparingInt(p -> p.getChildren().size()))
                .collect(Collectors.toList());
    }
}
