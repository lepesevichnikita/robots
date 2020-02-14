/*
 * DictionaryReaderTest
 *
 * practice
 *
 * 11:05
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DictionaryReaderTest {

  private static final String EMPTY_TXT = "empty.txt";
  private static final String WORDS_SPLITTED_BY_LINES_TXT = "words_splitted_by_lines.txt";
  private static final String WORDS_AND_EMPTY_LINES_TXT = "words_and_empty_lines.txt";
  private static final String WORDS_IN_SAME_LINE_IN_COLUMNS_AND_WITH_EMPTY_LINES_TXT = "words_in_same_line_in_columns_and_with_empty_lines.txt";
  private DictionaryReader dictionaryReader;

  @BeforeMethod
  public void initialize() {
    dictionaryReader = new DictionaryReader();
  }

  @Test
  public void createsEmptyDictionaryFromEmptyFile() {
    dictionaryReader.setFileName(EMPTY_TXT);
    assertThat(Arrays.asList(dictionaryReader.readDictionary()), is(empty()));
  }

  @Test()
  public void readsWordsSplitedByLines() {
    String[] expectedWords = new String[]{"first", "second", "third"};
    dictionaryReader.setFileName(WORDS_SPLITTED_BY_LINES_TXT);
    assertThat(Arrays.asList(dictionaryReader.readDictionary()), contains(expectedWords));
  }

  @Test
  public void skipsEmptyLines() {
    String[] expectedWords = new String[]{"first", "second", "fourth"};
    dictionaryReader.setFileName(WORDS_AND_EMPTY_LINES_TXT);
    assertThat(Arrays.asList(dictionaryReader.readDictionary()), contains(expectedWords));
  }

  @Test
  public void readsWordsInSameLineColumnsAndSkipsEmptyLines() {
    String[] expectedWords = new String[]{"first", "second", "four", "five", "six"};
    dictionaryReader.setFileName(WORDS_IN_SAME_LINE_IN_COLUMNS_AND_WITH_EMPTY_LINES_TXT);
    assertThat(Arrays.asList(dictionaryReader.readDictionary()), contains(expectedWords));
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionIfFileNameNotPassed() {
    dictionaryReader.readDictionary();
  }

}
