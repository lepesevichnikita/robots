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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordsContainerTest {

  @DataProvider
  public static Object[][] wordsData() {
    return new Object[][]{
        {1, new String[]{""}, new String[]{}},
        {1, new String[]{"first", "cat", "an"}, new String[]{}},
        {0, new String[]{""}, new String[]{""}},
        {0, new String[]{}, new String[]{}},
        {2, new String[]{"first", "cat", "an"}, new String[]{"an"}},
        {
            3,
            new String[]{null, "cat", "tom", "and", "mouse", "jerry"},
            new String[]{"cat", "tom", "and"}
        },
        {4, new String[]{null, "cat", "tom", "and", "mouse", "jerry"}, new String[]{}},
        {5, new String[]{"cat", "tom", "and", "mouse", "jerry"}, new String[]{"mouse", "jerry"}},
        {0, new String[]{null, null, null, null, null}, new String[]{}}
    };
  }

  @Test(dataProvider = "wordsData")
  public void addsItemsOnlyOfSameLength(
      int wordsLength, String[] sourceWords, String[] addedWords) {
    WordsContainer wordsContainer = new WordsContainer(wordsLength);
    for (String sourceWord : sourceWords) {
      wordsContainer.addWord(sourceWord);
    }
    assertThat(wordsContainer.getWords()
                             .toArray(), equalTo(addedWords));
  }
}
