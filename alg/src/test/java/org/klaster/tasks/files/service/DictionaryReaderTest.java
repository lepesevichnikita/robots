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

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DictionaryReaderTest {

  private DictionaryReader dictionaryReader;

  @BeforeClass
  public void initialize() {
    dictionaryReader = new DictionaryReader();
  }

  @Test
  public void createsEmptyDictionaryFromEmptyFile() throws IOException {
    final String emptyFileName = "empty.txt";
    dictionaryReader.setFileName(emptyFileName);
    assertThat(dictionaryReader.readGroupedDictionary(), is(empty()));
  }

  @Test(expectedExceptions = FileNotFoundException.class)
  public void throwsFileNotFoundExceptionAtNonExistedFileReading() throws IOException {
    final String notExistedFileName = "dont_exists.txt";
    dictionaryReader.setFileName(notExistedFileName);
    dictionaryReader.readGroupedDictionary();
  }

  @Test()
  public void readsWordsSplitedByLines() throws IOException {
    final String wordsSplittedByLinesFileName = "words_spliеted_by_lines.txt";
    final String[] expectedWords = new String[]{"first", "second", "third"};
    dictionaryReader.setFileName(wordsSplittedByLinesFileName);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

  @Test
  public void skipsEmptyLines() throws IOException {
    final String wordsAndEmptyLinesFileName = "words_and_empty_lines.txt";
    final String[] expectedWords = new String[]{"first", "second", "fourth"};
    dictionaryReader.setFileName(wordsAndEmptyLinesFileName);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

  @Test
  public void readsWordsInSameLine() throws IOException {
    final String wordsInSameLineFileName = "words_in_same_line.txt";
    final String[] expectedWords = new String[]{"first", "second", "third"};
    dictionaryReader.setFileName(wordsInSameLineFileName);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

  @Test
  public void readsWordsInSameLineColumnsAndSkipsEmptyLines() throws IOException {
    final String wordsInSameLineInColumnsAndWithEmptyLines = "words_in_same_line_in_columns_and_with_empty_lines.txt";
    final String[] expectedWords = new String[]{"first", "second", "four", "five", "six"};
    dictionaryReader.setFileName(wordsInSameLineInColumnsAndWithEmptyLines);
    assertThat(dictionaryReader.readDictionary(), contains(expectedWords));
  }

}