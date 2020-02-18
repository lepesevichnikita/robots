/*
 * WordsContainerTest
 *
 * practice
 *
 * 14:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WordsContainerTest {

  @Test
  @Parameters({"wordsLength", "sourceWords", "addedWords"})
  public void addsItemsOnlyOfSameLength(
      int wordsLength, String sourceWords, String addedWords) {
    WordsContainer wordsContainer = new WordsContainer(wordsLength);
    Arrays.stream(sourceWords.split(" "))
          .map(word -> word.equals("null")
                       ? null
                       : word)
          .forEach(word -> {
            wordsContainer.addWord(word);
          });
    String[] expectedWords = addedWords.split(" ");
    assertThat(wordsContainer.getWords()
                             .toArray(), equalTo(expectedWords));
  }
}
