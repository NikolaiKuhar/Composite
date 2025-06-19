package lt.esdc.text;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.exception.TextException;
import lt.esdc.text.parser.Parser;
import lt.esdc.text.parser.impl.*;
import lt.esdc.text.reader.TextReader;
import lt.esdc.text.service.TextService;
import lt.esdc.text.service.impl.*;
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

            // Парсинг
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

            // Сортировка абзацев
            TextService<List<TextComponent>> sortService = new SortParagraphsBySentenceCount();
            List<TextComponent> sorted = sortService.execute(parsedText);
            logger.info("Сортировка абзацев по количеству предложений:");
            sorted.forEach(p -> logger.info(p.toString()));

            // Предложение с самым длинным словом
            FindSentencesWithLongestWord longestFinder = new FindSentencesWithLongestWord();
            List<TextComponent> longest = longestFinder.execute(parsedText);
            logger.info("Предложение с самым длинным словом:\n{}", longest);

            // Удаление коротких предложений
            TextService<TextComponent> remover = new RemoveSentencesWithWordCountLessThan(3);
            TextComponent filtered = remover.execute(parsedText);
            logger.info("Текст без предложений с < 3 слов:\n{}", filtered);

            // Повторяющиеся слова
            FindAllRepeatedWords counter = new FindAllRepeatedWords();
            Map<String, Long> repeated = counter.execute(parsedText);
            logger.info("Повторяющиеся слова: {}", repeated);

            // Гласные и согласные
            TextService<Map<TextComponent, int[]>> vowelService = new CountVowelsAndConsonants();
            Map<TextComponent, int[]> result = vowelService.execute(parsedText);
            result.forEach((sentence, count) ->
                    logger.info("V: {}, C: {} → {}", count[0], count[1], sentence.toString()));



        } catch (TextException e) {
            logger.error("Ошибка в процессе обработки текста", e);
        }
    }
}
