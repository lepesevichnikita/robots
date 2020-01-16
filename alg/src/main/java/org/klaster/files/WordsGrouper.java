/*
 * WordsGrouper
 *
 * practice
 *
 * 16:55
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.files;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WordsGrouper {

  public List<WordsContainer> groupWordsByLength(String[] dictionary) {
    final List<String> notNullAndNonEmptyWords = skipNullAndEmptyWords(dictionary);
    final int maximumWordsLength = getMaximumWordsLength(notNullAndNonEmptyWords);
    final List<WordsContainer> wordsContainers = new LinkedList<>();
    for (int currentWordsContainerNumber = 0; currentWordsContainerNumber < maximumWordsLength;
        currentWordsContainerNumber++) {
      final int currentWordsLength = currentWordsContainerNumber + 1;
      final WordsContainer wordsContainer = new WordsContainer(currentWordsLength);
      getWordsByLength(notNullAndNonEmptyWords, currentWordsLength)
          .forEach(wordsContainer::addWord);
      wordsContainers.add(wordsContainer);
    }
    return wordsContainers;
  }

  private List<String> getWordsByLength(List<String> dictionary, int currentWordsLength) {
    return dictionary.stream().filter((String word) -> word.length() == currentWordsLength)
        .collect(Collectors.toList());
  }

  private int getMaximumWordsLength(List<String> dictionary) {
    int maximumWordsLength = 0;
    if (!dictionary.isEmpty()) {
      dictionary.sort(Comparator.comparingInt(String::length));
      maximumWordsLength = dictionary.get(dictionary.size() - 1).length();
    }
    return maximumWordsLength;
  }

  private List<String> skipNullAndEmptyWords(String[] dictionary) {
    return Arrays.stream(dictionary).filter(Objects::nonNull)
        .filter((String word) -> !word.isEmpty())
        .collect(Collectors.toList());
  }
}

