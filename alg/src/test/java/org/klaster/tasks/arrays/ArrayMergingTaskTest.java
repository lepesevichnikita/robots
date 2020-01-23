/*
 * ArrayMergingTaskTest
 *
 * practice
 *
 * 12:14
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class ArrayMergingTaskTest {

  private ArrayMergingTask arrayMergingTask;

  @Test
  public void sortsFirstArray() {
    Integer[] sourceFirstArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedFirstArray = new Integer[]{1, 2, 3, 4, 8};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, new Integer[]{});
    assertThat(arrayMergingTask.getFirstArray(), equalTo(expectedFirstArray));
  }

  @Test
  public void sortsSecondsArray() {
    Integer[] sourceSecondsArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedSecondArray = new Integer[]{1, 2, 3, 4, 8};
    arrayMergingTask = new ArrayMergingTask(new Integer[]{}, sourceSecondsArray);
    assertThat(arrayMergingTask.getSecondArray(), equalTo(expectedSecondArray));
  }

  @Test
  public void mergesTwoArrays() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, 3, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, -4, -5, -6};
    Integer[] expectedMergedArray = new Integer[]{-6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, sourceSecondArray);
    assertThat(arrayMergingTask.getMergedArray(), equalTo(expectedMergedArray));
  }

  @Test
  public void mergedArraysIsEqualToFirstSortedArrayIfSecondArrayIsEmpty() {
    Integer[] sourceFirstArray = new Integer[]{8, 4, 3, 2, 1};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, new Integer[]{});
    assertThat(arrayMergingTask.getMergedArray(), equalTo(arrayMergingTask.getFirstArray()));
  }

  @Test
  public void mergedArraysIsEqualToSecondSortedArrayIfFirstArrayIsEmpty() {
    Integer[] sourceSecondArray = new Integer[]{8, 4, 3, 2, 1};
    arrayMergingTask = new ArrayMergingTask(new Integer[]{}, sourceSecondArray);
    assertThat(arrayMergingTask.getMergedArray(), equalTo(arrayMergingTask.getSecondArray()));
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionOnArraysWithNull() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, null, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, null, 4, -5, -6};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, sourceSecondArray);
  }
}