/*
 * WordsContainer
 *
 * practice
 *
 * 14:33
 *
 * Copyright(c) Nikita Lepesevich
 */
package org.klaster.files;

import java.util.LinkedList;
import java.util.List;

/**
 * This class stores words of same length
 */
public class WordsContainer {

  private List<String> words;
  private int wordsLength;

  public WordsContainer(int wordsLength) {
    words = new LinkedList<>();
    this.wordsLength = wordsLength;
  }

  public void addWord(String word) {
    if (word != null && getWordsLength() == word.length()) {
      words.add(word);
    }
  }

  public int getWordsNumber() {
    return words.size();
  }

  public int getWordsLength() {
    return wordsLength;
  }

  public boolean containsWord(String word) {
    return words.contains(word);
  }

  public List<String> getWords() {
    return words;
  }
}
