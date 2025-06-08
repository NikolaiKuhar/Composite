package lt.esdc.text.component;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    List<TextComponent> getChildren();

    ComponentType getType();
    String toString(); // переопределим для сборки текста
}
