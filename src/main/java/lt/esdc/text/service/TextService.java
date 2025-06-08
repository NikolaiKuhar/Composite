package lt.esdc.text.service;

import lt.esdc.text.component.TextComponent;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphsBySentenceCount(TextComponent text);

    List<TextComponent> findSentencesWithLongestWord(TextComponent text);

    void removeSentencesWithWordCountLessThan(TextComponent text, int limit);

    Map<String, Long> findAllRepeatedWords(TextComponent text);

    Map<TextComponent, int[]> countVowelsAndConsonants(TextComponent text);
}
