package lt.esdc.text;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.exception.TextException;
import lt.esdc.text.parser.Parser;
import lt.esdc.text.parser.impl.*;
import lt.esdc.text.reader.TextReader;
import lt.esdc.text.service.TextService;
import lt.esdc.text.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws URISyntaxException {
        try {
            Path path = Path.of(ClassLoader.getSystemResource("text.txt").toURI());
            TextReader reader = new TextReader();
            String rawText = reader.read(path);

            Parser textParser = new TextParser();
            Parser paragraphParser = new ParagraphParser();
            Parser sentenceParser = new SentenceParser();
            Parser lexemeParser = new LexemeParser();
            Parser symbolParser = new SymbolParser();

            textParser.setNext(paragraphParser);
            paragraphParser.setNext(sentenceParser);
            sentenceParser.setNext(lexemeParser);
            lexemeParser.setNext(symbolParser);

            TextComponent parsedText = textParser.parse(rawText);
            logger.info("Текст успешно распарсен");

            TextService service = new TextServiceImpl();

            List<TextComponent> sortedParagraphs = service.sortParagraphsBySentenceCount(parsedText);
            logger.info("Сортировка абзацев по количеству предложений:");
            sortedParagraphs.forEach(p -> logger.info(p.toString()));

            List<TextComponent> longestSentences = service.findSentencesWithLongestWord(parsedText);
            logger.info("Предложения с самым длинным словом:");
            longestSentences.forEach(s -> logger.info(s.toString()));

            service.removeSentencesWithWordCountLessThan(parsedText, 3);
            logger.info("Удалены предложения с количеством слов < 3");

            Map<String, Long> repeatedWords = service.findAllRepeatedWords(parsedText);
            logger.info("Повторяющиеся слова: {}", repeatedWords);

            Map<TextComponent, int[]> vowelConsonantCount = service.countVowelsAndConsonants(parsedText);
            vowelConsonantCount.forEach((sentence, count) ->
                    logger.info("V: {}, C: {} | {}", count[0], count[1], sentence.toString())
            );

        } catch (TextException e) {
            logger.error("Ошибка в процессе обработки текста", e);
        }
    }
}
