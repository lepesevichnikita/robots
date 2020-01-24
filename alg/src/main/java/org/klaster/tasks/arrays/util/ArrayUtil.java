/*
 * ArrayUtil
 *
 * practice
 *
 * 16:17
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays.util;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayUtil {

  private ArrayUtil() {
  }

  public static <T> T[] reverseArrayWithoutCreatingSupportingArrays(T[] array) {
    T[] reversedArray = Arrays.copyOf(array, array.length);
    int arrayLength = array.length;
    int endPosition = arrayLength / 2;
    IntStream.range(0, endPosition)
             .forEach(currentPosition -> {
               int swappedItemPosition = arrayLength - currentPosition - 1;
               T tempItem = reversedArray[currentPosition];
               reversedArray[currentPosition] = reversedArray[swappedItemPosition];
               reversedArray[swappedItemPosition] = tempItem;
             });
    return reversedArray;
  }

  public static Integer[] mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(Integer[] firstArray, Integer[] secondArray) {
    Integer[] mergedArray = new Integer[firstArray.length + secondArray.length];
    Integer firstArrayItemIndex = 0;
    Integer secondArrayItemIndex = 0;
    Integer mergedArrayItemIndex = 0;
    Arrays.sort(firstArray);
    Arrays.sort(secondArray);
    while (firstArrayItemIndex < firstArray.length && secondArrayItemIndex < secondArray.length) {
      mergedArray[mergedArrayItemIndex++] =
          firstArray[firstArrayItemIndex] < secondArray[secondArrayItemIndex]
          ? firstArray[firstArrayItemIndex++]
          : secondArray[secondArrayItemIndex++];
    }
    completeMergedArrayWithUnmergedArray(firstArray, firstArrayItemIndex, mergedArray, mergedArrayItemIndex);
    completeMergedArrayWithUnmergedArray(secondArray, secondArrayItemIndex, mergedArray, mergedArrayItemIndex);
    return mergedArray;
  }


  private static void completeMergedArrayWithUnmergedArray(Integer[] uncompletedArray,
                                                           Integer uncompletedArrayItemIndex,
                                                           Integer[] mergedArray,
                                                           Integer mergedArrayItemIndex) {
    while (uncompletedArrayItemIndex < uncompletedArray.length) {
      mergedArray[mergedArrayItemIndex++] = uncompletedArray[uncompletedArrayItemIndex++];
    }
  }
}
