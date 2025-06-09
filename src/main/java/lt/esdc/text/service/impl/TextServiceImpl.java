package lt.esdc.text.service.impl;

import lt.esdc.text.component.ComponentType;
import lt.esdc.text.component.TextComponent;
import lt.esdc.text.service.TextService;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]+");
    private static final Set<Character> VOWELS = Set.of(
            'a', 'e', 'i', 'o', 'u', 'y',
            'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я'
    );

    @Override
    public List<TextComponent> sortParagraphsBySentenceCount(TextComponent text) {
        return text.getChildren().stream()
                .sorted(Comparator.comparingInt(p -> p.getChildren().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) {
        List<TextComponent> result = new ArrayList<>();
        int maxLength = 0;

        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                int localMax = sentence.getChildren().stream()
                        .mapToInt(lexeme -> lexeme.toString().replaceAll("[^a-zA-Zа-яА-Я]", "").length())
                        .max().orElse(0);

                if (localMax > maxLength) {
                    result.clear();
                    result.add(sentence);
                    maxLength = localMax;
                } else if (localMax == maxLength) {
                    result.add(sentence);
                }
            }
        }

        return result;
    }

    @Override
    public void removeSentencesWithWordCountLessThan(TextComponent text, int limit) {
        for (TextComponent paragraph : text.getChildren()) {
            paragraph.getChildren().removeIf(sentence -> {
                long wordCount = sentence.getChildren().stream()
                        .filter(lexeme -> WORD_PATTERN.matcher(lexeme.toString()).find())
                        .count();
                return wordCount < limit;
            });
        }
    }

    @Override
    public Map<String, Long> findAllRepeatedWords(TextComponent text) {
        return text.getChildren().stream() // paragraphs
                .flatMap(p -> p.getChildren().stream()) // sentences
                .flatMap(s -> s.getChildren().stream()) // lexemes
                .map(l -> l.toString().replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase())
                .filter(w -> !w.isEmpty())
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<TextComponent, int[]> countVowelsAndConsonants(TextComponent text) {
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
