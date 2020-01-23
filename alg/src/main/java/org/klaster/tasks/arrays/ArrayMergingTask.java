/*
 * ArrayMergingTask
 *
 * practice
 *
 * 11:53
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays;

import java.util.Arrays;

public class ArrayMergingTask {

  private final Integer[] firstArray;
  private final Integer[] secondArray;
  private final Integer[] mergedArray;

  public ArrayMergingTask(Integer[] firstArray, Integer[] secondArray) {
    this.firstArray = firstArray;
    this.secondArray = secondArray;
    this.mergedArray = new Integer[firstArray.length + secondArray.length];
    sortArrays();
    mergeArrays();
  }

  public Integer[] getFirstArray() {
    return firstArray;
  }

  public Integer[] getSecondArray() {
    return secondArray;
  }

  public Integer[] getMergedArray() {
    return mergedArray;
  }

  private void sortArrays() {
    Arrays.sort(firstArray);
    Arrays.sort(secondArray);
  }

  private void mergeArrays() {
    int firstArrayItemIndex = 0;
    int secondArrayItemIndex = 0;
    int mergedArrayItemIndex = 0;
    while (firstArrayItemIndex < firstArray.length && secondArrayItemIndex < secondArray.length) {
      mergedArray[mergedArrayItemIndex++] =
          firstArray[firstArrayItemIndex] < secondArray[secondArrayItemIndex]
          ? firstArray[firstArrayItemIndex++]
          : secondArray[secondArrayItemIndex++];
    }
    while (firstArrayItemIndex < firstArray.length) {
      mergedArray[mergedArrayItemIndex++] = firstArray[firstArrayItemIndex++];
    }
    while (secondArrayItemIndex < secondArray.length) {
      mergedArray[mergedArrayItemIndex++] = secondArray[secondArrayItemIndex++];
    }

  }
}
