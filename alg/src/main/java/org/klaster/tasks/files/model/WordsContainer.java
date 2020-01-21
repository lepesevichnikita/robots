/*
 * WordsContainer
 *
 * practice
 *
 * 14:33
 *
 * Copyright(c) Nikita Lepesevich
 */
package org.klaster.tasks.files.model;

import java.util.LinkedList;
import java.util.List;

/**
 * This class stores words of same length
 */
public class WordsContainer {

  private List<String> words;
  private Integer wordsLength;

  public WordsContainer(Integer wordsLength) {
    words = new LinkedList<>();
    this.wordsLength = wordsLength;
  }

  public void addWord(String word) {
    if (word != null && getWordsLength() == word.length()) {
      words.add(word);
    }
  }

  public Integer getWordsLength() {
    return wordsLength;
  }

  public Integer getSize() {
    return words.size();
  }

  public Boolean containsPrefix(String prefix) {
    return words.stream().anyMatch((String word) -> word.startsWith(prefix));
  }

  public List<String> getWords() {
    return words;
  }

  public Boolean isEmpty() {
    return words.isEmpty();
  }
}
