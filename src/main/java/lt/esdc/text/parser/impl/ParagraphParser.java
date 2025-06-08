package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s+";

    @Override
    public TextComponent parse(String paragraph) {
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        String[] sentences = paragraph.strip().split(SENTENCE_DELIMITER);

        for (String sentence : sentences) {
            TextComponent sentenceComponent = next.parse(sentence.strip());
            paragraphComposite.add(sentenceComponent);
        }

        return paragraphComposite;
    }
}
