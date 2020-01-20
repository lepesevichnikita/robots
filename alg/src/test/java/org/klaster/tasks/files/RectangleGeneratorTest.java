/*
 * RectangleGeneratorTest
 *
 * practice
 *
 * 12:36
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RectangleGeneratorTest {

  private final RectangleGenerator rectangleGenerator = new RectangleGenerator();
  private final WordsGrouper wordsGrouper = new WordsGrouper();

  @DataProvider
  public static Object[][] wordsAndRectangles() {
    return new Object[][]{
        {new String[]{"agent", "house", "notch", "ahn", "goo", "eut", "nsc", "teh"},
            new String[]{"agent", "house", "notch"}},
        {new String[]{"a"}, new String[]{"a"}},
        {new String[]{"aa"}, new String[]{"aa", "aa"}},
        {new String[]{"123", "321", "478", "134", "227", "318"}, new String[]{"123", "321", "478"}},
    };
  }

  @Test(dataProvider = "wordsAndRectangles")
  public void createsRectangles(String[] dictionary, String[] expectedRows) {
    wordsGrouper.setDictionary(dictionary);
    rectangleGenerator.setWordsContainers(wordsGrouper.groupWordsByLength());
    assertThat(rectangleGenerator.generateMaximumPossibleRectangle().getRows(),
        contains(expectedRows));
  }

}