/*
 * WordsGrouper
 *
 * practice
 *
 * 16:55
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.klaster.tasks.files.model.WordsContainer;

public class WordsGrouper {

  private String[] dictionary;

  public List<WordsContainer> groupWordsByLength() {
    final Integer maximumWordsLength = getMaximumWordsLength();
    List<WordsContainer> wordsContainers = new ArrayList<>();
    IntStream.rangeClosed(1, maximumWordsLength).boxed()
             .map(WordsContainer::new)
             .map(this::fillWordsContainer)
             .forEach(wordsContainers::add);
    return wordsContainers;
  }

  public void setDictionary(String[] dictionary) {
    this.dictionary = dictionary;
  }

  private WordsContainer fillWordsContainer(WordsContainer wordsContainer) {
    List<String> nullAndEmptyWords = skipNullAndEmptyWords(dictionary);
    nullAndEmptyWords.stream()
                     .filter(word -> word.length() == wordsContainer.getWordsLength())
                     .forEach(wordsContainer::addWord);
    return wordsContainer;
  }

  private Integer getMaximumWordsLength() {
    List<String> nullAndEmptyWords = skipNullAndEmptyWords(dictionary);
    return nullAndEmptyWords.stream()
                            .map(String::length)
                            .max(Comparator.comparingInt(length -> length))
                            .orElse(0);
  }

  private List<String> skipNullAndEmptyWords(String[] dictionary) {
    return Arrays.stream(dictionary)
                 .filter(Objects::nonNull)
                 .filter((String word) -> !word.isEmpty())
                 .collect(Collectors.toList());
  }
}

