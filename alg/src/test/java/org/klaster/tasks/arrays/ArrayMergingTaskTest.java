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
import static org.hamcrest.Matchers.contains;

import java.util.Arrays;
import org.klaster.tasks.arrays.task.ArrayMergingTask;
import org.testng.annotations.Test;

public class ArrayMergingTaskTest {

  private ArrayMergingTask arrayMergingTask;

  @Test
  public void sortsFirstArray() {
    Integer[] sourceFirstArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedFirstArray = new Integer[]{1, 2, 3, 4, 8};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, new Integer[]{});
    assertThat(Arrays.asList(arrayMergingTask.getFirstArray()), contains(expectedFirstArray));
  }

  @Test
  public void sortsSecondsArray() {
    Integer[] sourceSecondsArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedSecondArray = new Integer[]{1, 2, 3, 4, 8};
    arrayMergingTask = new ArrayMergingTask(new Integer[]{}, sourceSecondsArray);
    assertThat(Arrays.asList(arrayMergingTask.getSecondArray()), contains(expectedSecondArray));
  }

  @Test
  public void mergesTwoArrays() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, 3, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, -4, -5, -6};
    Integer[] expectedMergedArray = new Integer[]{-6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, sourceSecondArray);
    assertThat(Arrays.asList(arrayMergingTask.getMergedArray()), contains(expectedMergedArray));
  }

  @Test
  public void mergedArraysIsEqualToFirstSortedArrayIfSecondArrayIsEmpty() {
    Integer[] sourceFirstArray = new Integer[]{8, 4, 3, 2, 1};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, new Integer[]{});
    assertThat(Arrays.asList(arrayMergingTask.getMergedArray()), contains(arrayMergingTask.getFirstArray()));
  }

  @Test
  public void mergedArraysIsEqualToSecondSortedArrayIfFirstArrayIsEmpty() {
    Integer[] sourceSecondArray = new Integer[]{8, 4, 3, 2, 1};
    arrayMergingTask = new ArrayMergingTask(new Integer[]{}, sourceSecondArray);
    assertThat(Arrays.asList(arrayMergingTask.getMergedArray()), contains(arrayMergingTask.getSecondArray()));
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionOnArraysWithNull() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, null, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, null, 4, -5, -6};
    arrayMergingTask = new ArrayMergingTask(sourceFirstArray, sourceSecondArray);
  }
}