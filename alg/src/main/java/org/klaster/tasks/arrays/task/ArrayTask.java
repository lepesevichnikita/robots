/*
 * ArrayTask
 *
 * practice
 *
 * 11:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays.task;

import org.klaster.tasks.arrays.util.ArrayUtil;

/**
 * Class to reverse array without additional array using
 */
public class ArrayTask {

  private ArrayTask() {
  }

  public static <T> T[] reverseArrayWithoutCreatingSupportingArrays(T[] array) {
    return ArrayUtil.reverseArrayWithoutCreatingSupportingArrays(array);
  }

  public static Integer[] mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(Integer[] firstArray, Integer[] secondArray) {
    return ArrayUtil.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(firstArray, secondArray);
  }
}
