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

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.klaster.tasks.files.service.RectangleGeneratorTest;
import org.klaster.util.TestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordsContainerTest {

  private static final String SOURCES_FOLDER = RectangleGeneratorTest.class.getResource("/words_container")
                                                                           .getPath();

  @DataProvider
  public Object[][] wordsLengthSourceWordsAndAddedWords() {
    List<File> files = TestUtil.getFilesFromFolder(new File(SOURCES_FOLDER), ".csv");
    Object[][] arguments = TestUtil.getValuesFromCsvFiles(files);
    Arrays.stream(arguments)
          .forEach(argumentsList -> {
            argumentsList[0] = Integer.parseInt(String.valueOf(argumentsList[0]));
            argumentsList[1] = TestUtil.arrayOfStringFromString(String.valueOf(argumentsList[1]), " ");
            argumentsList[2] = TestUtil.arrayOfStringFromString(String.valueOf(argumentsList[2]), " ");
          });
    return arguments;
  }

  @Test(dataProvider = "wordsLengthSourceWordsAndAddedWords")
  public void addsItemsOnlyOfSameLength(int wordsLength, String[] sourceWords, String[] addedWords) {
    WordsContainer wordsContainer = new WordsContainer(wordsLength);
    Arrays.stream(sourceWords)
          .forEach(wordsContainer::addWord);
    assertThat(wordsContainer.getWords()
                             .toArray(), equalTo(addedWords));
  }
}
