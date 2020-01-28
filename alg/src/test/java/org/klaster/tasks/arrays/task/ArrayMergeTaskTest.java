/*
 * org.klaster.tasks.arrays.task
 *
 * robots
 *
 * 1/27/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyArray;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ArrayMergeTaskTest {

  private ArrayMergeTask arrayMergeTask;

  @BeforeClass
  public void initialize() {
    arrayMergeTask = new ArrayMergeTask();
  }

  @Test
  public void mergesTwoArrays() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, 3, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, -4, -5, -6};
    Integer[] expectedMergedArray = new Integer[]{-6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6};
    arrayMergeTask.setArrays(sourceFirstArray, sourceSecondArray);
    List<Integer> actualMergedArrayAsList = Arrays.asList(arrayMergeTask.mergeArrayWithoutResultArraySorting());
    assertThat(actualMergedArrayAsList, contains(expectedMergedArray));
  }

  @Test
  public void mergedArraysIsEqualToFirstSortedArrayIfSecondArrayIsEmpty() {
    Integer[] sourceFirstArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedMergedArray = new Integer[]{1, 2, 3, 4, 8};
    arrayMergeTask.setArrays(sourceFirstArray, new Integer[]{});
    List<Integer> actualMergedArrayAsList = Arrays.asList(arrayMergeTask.mergeArrayWithoutResultArraySorting());
    assertThat(actualMergedArrayAsList, contains(expectedMergedArray));
  }

  @Test
  public void mergedArraysIsEqualToSecondSortedArrayIfFirstArrayIsEmpty() {
    Integer[] sourceSecondArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedMergedArray = new Integer[]{1, 2, 3, 4, 8};
    arrayMergeTask.setArrays(new Integer[]{}, sourceSecondArray);
    List<Integer> actualMergedArrayAsList = Arrays.asList(arrayMergeTask.mergeArrayWithoutResultArraySorting());
    assertThat(actualMergedArrayAsList, contains(expectedMergedArray));
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionOnArraysWithNull() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, null, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, null, 4, -5, -6};
    arrayMergeTask.setArrays(sourceFirstArray, sourceSecondArray);
    arrayMergeTask.mergeArrayWithoutResultArraySorting();
  }

  @Test
  public void mergesTwoEmptyArrayIntoSingleEmptyArray() {
    Integer[] sourceFirstEmptyArray = new Integer[]{};
    Integer[] sourceSecondEmptyArray = new Integer[]{};
    arrayMergeTask.setArrays(sourceFirstEmptyArray, sourceSecondEmptyArray);
    assertThat(arrayMergeTask.mergeArrayWithoutResultArraySorting(), emptyArray());
  }
}