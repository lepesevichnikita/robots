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

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.klaster.tasks.files.model.WordsContainer;
import org.klaster.util.TestUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordsGrouperTest {

  private WordsGrouper wordsGrouper;

  private static final String WORDS_GROUPER_CORRECT_NUMBER_OF_CONTAINERS_FOLDER = RectangleGeneratorTest.class.getResource("/words_grouper/number_of_containers")
                                                                                                              .getPath();
  private static final String WORDS_GROUPER_SIZES_OF_CONTAINERS = RectangleGeneratorTest.class.getResource("/words_grouper/sizes_of_containers")
                                                                                              .getPath();

  @BeforeClass
  public void initialize() {
    wordsGrouper = new WordsGrouper();
  }

  @DataProvider
  public Object[][] dictionaryAndExpectedWordsContainerNumber() {
    List<File> files = TestUtil.getFilesFromFolder(new File(WORDS_GROUPER_CORRECT_NUMBER_OF_CONTAINERS_FOLDER), ".csv");
    Object[][] arguments = TestUtil.getValuesFromCsvFiles(files);
    Arrays.stream(arguments)
          .forEach(argumentsList -> {
            argumentsList[0] = TestUtil.arrayOfStringFromString(String.valueOf(argumentsList[0]), " ");
            argumentsList[1] = Integer.parseInt(String.valueOf(argumentsList[1]));
          });
    return arguments;
  }


  @DataProvider
  public Object[][] dictionaryAndExpectedSizesOfContainers() {
    List<File> files = TestUtil.getFilesFromFolder(new File(WORDS_GROUPER_SIZES_OF_CONTAINERS), ".csv");
    Object[][] arguments = TestUtil.getValuesFromCsvFiles(files);
    Arrays.stream(arguments)
          .forEach(argumentsList -> {
            argumentsList[0] = TestUtil.arrayOfStringFromString(String.valueOf(argumentsList[0]), " ");
            argumentsList[1] = TestUtil.arrayOfIntegersFromString(String.valueOf(argumentsList[1]), " ");
          });
    return arguments;
  }

  @Test(dataProvider = "dictionaryAndExpectedWordsContainerNumber")
  public void createsCorrectNumberOfContainers(String[] dictionary, int expectedWordsContainerNumber) {
    wordsGrouper.setDictionary(dictionary);
    assertThat(wordsGrouper.groupWordsByLength()
                           .size(), equalTo(expectedWordsContainerNumber));
  }

  @Test(dataProvider = "dictionaryAndExpectedSizesOfContainers")
  public void correctlyAddsWordsToContainers(String[] dictionary, Integer[] expectedContainersSizes) {
    wordsGrouper.setDictionary(dictionary);
    List<Integer> actualContainersSizes = wordsGrouper.groupWordsByLength()
                                                      .stream()
                                                      .map(WordsContainer::getSize)
                                                      .collect(Collectors.toList());
    assertThat(actualContainersSizes, contains(expectedContainersSizes));
  }
}