/*
 * WordsGrouperTest
 *
 * practice
 *
 * 16:58
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.stream.Collectors;
import org.klaster.tasks.files.model.WordsContainer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordsGrouperTest {

  private static final WordsGrouper WORDS_GROUPER = new WordsGrouper();

  @DataProvider
  public static Object[][] wordsAndContainersNumbers() {
    return new Object[][]{
        {new String[]{"a", "an", "word", "gem", "streak", "metro", "city"}, 6},
        {new String[]{"a", "an", "word", "gem", "streak", "metro", null}, 6},
        {new String[]{null}, 0},
        {new String[]{null, null, null, null}, 0},
        {new String[]{""}, 0},
        {new String[]{"a", "s"}, 1},
        {new String[]{"a", "an", "word", "gem", "streak", "metro", "city", "neo", "home", "sun"}, 6}
    };
  }

  @DataProvider
  public static Object[][] wordsAndContainersSizes() {
    return new Object[][]{
        {new String[]{"a", "an", "word", "gem", "streak", "metro", "city"},
            new Integer[]{1, 1, 1, 2, 1, 1}},
        {new String[]{"a", "an", "word", "gem", "streak", "metro", null},
            new Integer[]{1, 1, 1, 1, 1, 1}},
        {new String[]{"a", "s"}, new Integer[]{2}},
        {new String[]{"a", "word", "gem", "streak", "metro", "city", "neo", "home", "sun"},
            new Integer[]{1, 0, 3, 3, 1, 1}}
    };
  }

  @Test(dataProvider = "wordsAndContainersNumbers")
  public void createsCorrectNumberOfContainers(String[] dictionary,
                                               int expectedWordsContainerNumber) {
    WORDS_GROUPER.setDictionary(dictionary);
    assertThat(WORDS_GROUPER.groupWordsByLength().size(), equalTo(expectedWordsContainerNumber));
  }

  @Test(dataProvider = "wordsAndContainersSizes")
  public void correctlyAddsWordsToContainers(String[] dictionary,
                                             Integer[] expectedWordsContainersSizes) {
    WORDS_GROUPER.setDictionary(dictionary);
    List<Integer> actualContainersSizes = WORDS_GROUPER.groupWordsByLength().stream().map(WordsContainer::getSize)
                                                       .collect(Collectors.toList());
    assertThat(actualContainersSizes, contains(expectedWordsContainersSizes));
  }
}