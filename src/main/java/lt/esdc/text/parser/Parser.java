package lt.esdc.text.parser;

import lt.esdc.text.component.TextComponent;

public interface Parser {
    void setNext(Parser next);
    TextComponent parse(String data);
}
