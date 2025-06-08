package lt.esdc.text.util;

public final class TextConstants {

    private TextConstants() {}

    public static final String PARAGRAPH_DELIMITER = "\\n{2,}";
    public static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s+";
    public static final String LEXEME_DELIMITER = "\\s+";

    public static final String REGEX_WORD = "[a-zA-Zа-яА-Я]+";
    public static final String REGEX_EXPRESSION = "\\d+[\\d\\s+\\-*/()]*";
}
