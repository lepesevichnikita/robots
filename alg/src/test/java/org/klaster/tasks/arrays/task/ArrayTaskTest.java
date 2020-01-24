/*
 * ArrayTaskTest
 *
 * practice
 *
 * 12:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class ArrayTaskTest {

  @Test
  public void reverseAllArrayItems() {
    Integer[] sourceArray = new Integer[]{1, 2, 3, 4, 5, 6};
    Integer[] expectedArray = new Integer[]{6, 5, 4, 3, 2, 1};
    assertThat(Arrays.asList(ArrayTask.reverseArrayWithoutCreatingSupportingArrays(sourceArray)), contains(expectedArray));
  }

  @Test
  public void acceptsEmptyArrays() {
    Integer[] emptyArray = new Integer[]{};
    assertThat(Arrays.asList(ArrayTask.reverseArrayWithoutCreatingSupportingArrays(emptyArray)), empty());
  }

  @Test
  public void reversesArrayWithNulls() {
    Integer[] sourceArray = new Integer[]{null, 1, null, 2};
    Integer[] expectedResult = new Integer[]{2, null, 1, null};
    assertThat(Arrays.asList(ArrayTask.reverseArrayWithoutCreatingSupportingArrays(sourceArray)), contains(expectedResult));
  }

  @Test
  public void acceptsArraysOfNulls() {
    Integer[] sourceArray = new Integer[]{null, null, null, null};
    assertThat(Arrays.asList(ArrayTask.reverseArrayWithoutCreatingSupportingArrays(sourceArray)), contains(sourceArray));
  }

  @Test
  public void doesntChangeArrayWithSingleItem() {
    Integer[] sourceArray = new Integer[]{1};
    assertThat(ArrayTask.reverseArrayWithoutCreatingSupportingArrays(sourceArray), equalTo(sourceArray));
  }

  @Test
  public void mergesTwoArrays() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, 3, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, -4, -5, -6};
    Integer[] expectedMergedArray = new Integer[]{-6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6};
    List<Integer> actualMergedArrayAsList = Arrays.asList(
        ArrayTask.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(sourceFirstArray, sourceSecondArray));
    assertThat(actualMergedArrayAsList, contains(expectedMergedArray));
  }

  @Test
  public void mergedArraysIsEqualToFirstSortedArrayIfSecondArrayIsEmpty() {
    Integer[] sourceFirstArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedMergedArray = new Integer[]{1, 2, 3, 4, 8};
    List<Integer> actualMergedArrayAsList = Arrays.asList(
        ArrayTask.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(sourceFirstArray, new Integer[]{}));
    assertThat(actualMergedArrayAsList, contains(expectedMergedArray));
  }

  @Test
  public void mergedArraysIsEqualToSecondSortedArrayIfFirstArrayIsEmpty() {
    Integer[] sourceSecondArray = new Integer[]{8, 4, 3, 2, 1};
    Integer[] expectedMergedArray = new Integer[]{1, 2, 3, 4, 8};
    List<Integer> actualMergedArrayAsList = Arrays.asList(
        ArrayTask.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(new Integer[]{}, sourceSecondArray));
    assertThat(actualMergedArrayAsList, contains(expectedMergedArray));
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionOnArraysWithNull() {
    Integer[] sourceFirstArray = new Integer[]{1, 2, null, -1, -2, -3};
    Integer[] sourceSecondArray = new Integer[]{4, 5, 6, null, 4, -5, -6};
    ArrayTask.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(sourceFirstArray, sourceSecondArray);
  }
}
