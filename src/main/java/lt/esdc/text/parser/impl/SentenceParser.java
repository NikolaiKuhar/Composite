package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;

public class SentenceParser extends AbstractParser {

    private static final String LEXEME_DELIMITER = "\\s+";

    @Override
    public TextComponent parse(String sentence) {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = sentence.strip().split(LEXEME_DELIMITER);

        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = next.parse(lexeme);
            sentenceComposite.add(lexemeComponent);
        }

        return sentenceComposite;
    }
}
