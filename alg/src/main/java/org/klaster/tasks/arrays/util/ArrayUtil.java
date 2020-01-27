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

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayUtil {

  private ArrayUtil() {
  }

  public static boolean isArraySortedAscending(Integer[] array) {
    return IntStream.range(0, array.length - 1)
                    .allMatch(currentItemIndex -> array[currentItemIndex] <= array[currentItemIndex + 1]);
  }

  public static void validateArrayIsSortedAscending(Integer[] array, String message) {
    if (!isArraySortedAscending(array)) {
      throw new InvalidParameterException(message);
    }
  }

  public static <T> void validateArrayIsNotNull(T[] array, String message) {
    if (array == null) {
      throw new NullPointerException(message);
    }
  }

  public static <T> void validateArrayIsNotNull(T[] array) {
    validateArrayIsNotNull(array, "Array must be not null");
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
    validateArrayIsSortedAscending(firstArray, "First array must be sorted ascending");
    validateArrayIsSortedAscending(secondArray, "Second array must be sorted ascending");
    Integer[] mergedArray = new Integer[firstArray.length + secondArray.length];
    Integer firstArrayItemIndex = 0;
    Integer secondArrayItemIndex = 0;
    Integer mergedArrayItemIndex = 0;
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
