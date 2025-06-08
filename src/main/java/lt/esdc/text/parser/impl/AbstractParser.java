package lt.esdc.text.parser.impl;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.parser.Parser;

public abstract class AbstractParser implements Parser {
    protected Parser next;

    @Override
    public void setNext(Parser next) {
        this.next = next;
    }
}
