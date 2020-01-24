/*
 * FileTaskTest
 *
 * practice
 *
 * 11:06
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileTaskTest {

  private static final String WORDS_TO_GENERATE_RECTANGLE_FILE_NAME = "words_to_generate_rectangle.txt";
  private static final String WORDS_TO_NOT_GENERATE_RECTANGLE_FILE_NAME = "words_to_not_generate_rectangle_file_name.txt";
  private FileTask fileTask;

  @BeforeMethod
  public void initialize() {
    fileTask = new FileTask();
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionIfFileNameNotPassed() {
    fileTask.readDictionaryAndGenerateFirstMaximumPossibleRectangle();
  }

  @Test
  public void readsDictionaryAndGeneratesRectangleFromPassedFile() {
    String[] expectedRectangleWords = new String[]{"agent", "house", "notch"};
    fileTask.setFileName(WORDS_TO_GENERATE_RECTANGLE_FILE_NAME);
    assertThat(fileTask.readDictionaryAndGenerateFirstMaximumPossibleRectangle()
                       .getRows(), contains(expectedRectangleWords));
  }

  @Test
  public void returnsNullIfItIsImpossibleToGenerateRectangle() {
    fileTask.setFileName(WORDS_TO_NOT_GENERATE_RECTANGLE_FILE_NAME);
    assertThat(fileTask.readDictionaryAndGenerateFirstMaximumPossibleRectangle(), nullValue());
  }


}