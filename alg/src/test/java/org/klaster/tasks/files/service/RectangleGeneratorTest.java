/*
 * RectangleGeneratorTest
 *
 * practice
 *
 * 12:36
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RectangleGeneratorTest {

  private final RectangleGenerator rectangleGenerator = new RectangleGenerator();
  private final WordsGrouper wordsGrouper = new WordsGrouper();

  @Test
  @Parameters({"dictionary", "expectedRows"})
  public void createsRectangles(String dictionary, String expectedRows) {
    wordsGrouper.setDictionary(dictionary.split(" "));
    rectangleGenerator.setWordsContainers(wordsGrouper.groupWordsByLength());
    assertThat(rectangleGenerator.generateFirstMaximumPossibleRectangle()
                                 .getRows(), contains(expectedRows.split(" ")));
  }

  @Test
  public void returnsNullIfItIsImpossibleToGenerateRectangleFromPassedDictionary() {
    wordsGrouper.setDictionary(new String[]{"hey", "johnny", "you", "can", "stand", "it", "out"});
    rectangleGenerator.setWordsContainers(wordsGrouper.groupWordsByLength());
    assertThat(rectangleGenerator.generateFirstMaximumPossibleRectangle(), is(nullValue()));
  }
}