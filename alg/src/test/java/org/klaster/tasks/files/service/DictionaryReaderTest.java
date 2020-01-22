/*
 * DictionaryReaderTest
 *
 * practice
 *
 * 11:06
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DictionaryReaderTest {

  private static final String EMPTY_FILE_NAME = "empty.txt";
  private static final String WORDS_SPLITTED_BY_LINES_TXT = "words_splitted_by_lines.txt";
  private static final String WORDS_AND_EMPTY_LINES_TXT = "words_and_empty_lines.txt";
  private static final String WORDS_IN_SAME_LINE_IN_COLUMNS_AND_WITH_EMPTY_LINES_TXT = "words_in_same_line_in_columns_and_with_empty_lines.txt";
  private DictionaryReader dictionaryReader;

  @BeforeClass
  public void initialize() {
    dictionaryReader = new DictionaryReader(EMPTY_FILE_NAME);
  }

  @Test
  public void createsEmptyDictionaryFromEmptyFile() {
    dictionaryReader = new DictionaryReader(EMPTY_FILE_NAME);
    assertThat(dictionaryReader.readGroupedDictionary(), is(empty()));
  }

  @Test()
  public void readsWordsSplitedByLines() {
    final String[] expectedWords = new String[]{"first", "second", "third"};
    dictionaryReader = new DictionaryReader(WORDS_SPLITTED_BY_LINES_TXT);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

  @Test
  public void skipsEmptyLines() {
    final String[] expectedWords = new String[]{"first", "second", "fourth"};
    dictionaryReader = new DictionaryReader(WORDS_AND_EMPTY_LINES_TXT);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

  @Test
  public void readsWordsInSameLineColumnsAndSkipsEmptyLines() {
    final String[] expectedWords = new String[]{"first", "second", "four", "five", "six"};
    dictionaryReader = new DictionaryReader(WORDS_IN_SAME_LINE_IN_COLUMNS_AND_WITH_EMPTY_LINES_TXT);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

}