package lt.esdc.text.service.impl;

import lt.esdc.text.component.TextComponent;
import lt.esdc.text.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountVowelsAndConsonants implements TextService<Map<TextComponent, int[]>> {

    private static final Logger logger = LogManager.getLogger(CountVowelsAndConsonants.class);

    private static final Set<Character> VOWELS = Set.of(
            'a', 'e', 'i', 'o', 'u', 'y',
            'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я'
    );

    @Override
    public Map<TextComponent, int[]> execute(TextComponent text) {
        logger.info("Подсчёт гласных и согласных в предложениях");
        Map<TextComponent, int[]> result = new HashMap<>();

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int vowels = 0;
                int consonants = 0;

                String all = sentence.toString().toLowerCase();
                for (char ch : all.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        if (VOWELS.contains(ch)) {
                            vowels++;
                        } else {
                            consonants++;
                        }
                    }
                }

                result.put(sentence, new int[]{vowels, consonants});
            }
        }

        return result;
    }
}
