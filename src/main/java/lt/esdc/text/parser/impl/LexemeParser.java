package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;

public class LexemeParser extends AbstractParser {

    @Override
    public TextComponent parse(String lexeme) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);

        for (char ch : lexeme.toCharArray()) {
            TextComponent symbol = next.parse(Character.toString(ch));
            lexemeComposite.add(symbol);
        }

        return lexemeComposite;
    }
}
