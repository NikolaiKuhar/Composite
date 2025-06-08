package lt.esdc.text.component.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private final List<TextComponent> components = new ArrayList<>();
    private final ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return components;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent component : components) {
            sb.append(component.toString());
            if (type == ComponentType.PARAGRAPH) sb.append("\n");
            if (type == ComponentType.SENTENCE) sb.append(" ");
        }
        return sb.toString().trim();
    }
}
