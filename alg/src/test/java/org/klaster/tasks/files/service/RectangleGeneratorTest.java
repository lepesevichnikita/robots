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

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.klaster.util.TestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RectangleGeneratorTest {

  private final RectangleGenerator rectangleGenerator = new RectangleGenerator();
  private final WordsGrouper wordsGrouper = new WordsGrouper();

  private static final String SOURCES_FOLDER = RectangleGeneratorTest.class.getResource("/rectangle_generator")
                                                                           .getPath();

  @DataProvider
  public Object[][] dictionaryAndExpectedRows() {
    List<File> files = TestUtil.getFilesFromFolder(new File(SOURCES_FOLDER), ".csv");
    Object[][] arguments = TestUtil.getValuesFromCsvFiles(files);
    Arrays.stream(arguments)
          .forEach(argumentsList -> {
            argumentsList[0] = TestUtil.arrayOfStringFromString(String.valueOf(argumentsList[0]), " ");
            argumentsList[1] = TestUtil.arrayOfStringFromString(String.valueOf(argumentsList[1]), " ");
          });
    return arguments;
  }

  @Test(dataProvider = "dictionaryAndExpectedRows")
  public void createsRectangles(String[] dictionary, String[] expectedRows) {
    wordsGrouper.setDictionary(dictionary);
    rectangleGenerator.setWordsContainers(wordsGrouper.groupWordsByLength());
    assertThat(rectangleGenerator.generateFirstMaximumPossibleRectangle()
                                 .getRows(), contains(expectedRows));
  }

  @Test
  public void returnsNullIfItIsImpossibleToGenerateRectangleFromPassedDictionary() {
    wordsGrouper.setDictionary(new String[]{"hey", "johnny", "you", "can", "stand", "it", "out"});
    rectangleGenerator.setWordsContainers(wordsGrouper.groupWordsByLength());
    assertThat(rectangleGenerator.generateFirstMaximumPossibleRectangle(), is(nullValue()));
  }
}