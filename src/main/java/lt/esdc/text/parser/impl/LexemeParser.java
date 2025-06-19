package lt.esdc.text.parser.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.component.impl.TextComposite;
import lt.esdc.text.component.impl.TextLeaf;
import lt.esdc.text.interpreter.ExpressionInterpreter;
import lt.esdc.text.interpreter.ExpressionParser;
import lt.esdc.text.util.TextConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractParser {

    private static final Logger logger = LogManager.getLogger(LexemeParser.class);

    private final ExpressionParser expressionParser = new ExpressionParser();
    private final SymbolParser symbolParser = new SymbolParser(); // для обычных символов

    @Override
    public TextComponent parse(String lexeme) {
        logger.info("Парсинг лексемы: {}", lexeme);

        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);

        if (lexeme == null || lexeme.isBlank()) {
            logger.warn("Пустая лексема пропущена");
            return lexemeComposite;
        }

        if (isExpression(lexeme)) {
            try {
                ExpressionInterpreter expression = expressionParser.parse(lexeme);
                double result = expression.interpret();
                logger.info("Результат выражения: {} = {}", lexeme, result);
                addDigits(result, lexemeComposite);
                return lexemeComposite;
            } catch (Exception e) {
                logger.warn("Ошибка интерпретации выражения '{}': {}", lexeme, e.getMessage());
            }
        }

        // обычная лексема — парсим по символам
        for (char ch : lexeme.toCharArray()) {
            TextComponent symbol = symbolParser.parse(String.valueOf(ch));
            lexemeComposite.add(symbol);
        }

        return lexemeComposite;
    }

    private boolean isExpression(String lexeme) {
        return lexeme.matches(TextConstants.REGEX_EXPRESSION);
    }

    private void addDigits(double value, TextComposite parent) {
        String result = (value % 1 == 0) ? String.valueOf((int) value) : String.valueOf(value);
        for (char ch : result.toCharArray()) {
            parent.add(new TextLeaf(ch, ComponentType.SYMBOL));
        }
    }
}
