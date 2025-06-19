package lt.esdc.text.service;

import lt.esdc.text.component.TextComponent;

public interface TextService<T> {
    T execute(TextComponent text);
}

