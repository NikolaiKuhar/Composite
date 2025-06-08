package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextLeaf;

public class SymbolParser extends AbstractParser {

    @Override
    public TextComponent parse(String data) {
        char ch = data.charAt(0);
        return new TextLeaf(ch, ComponentType.SYMBOL);
    }
}
