package lt.esdc.text.component.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {

    private final char symbol;
    private final ComponentType type;

    public TextLeaf(char symbol, ComponentType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Leaf node can't add children");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Leaf node can't remove children");
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
