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

import java.util.Arrays;
import org.klaster.tasks.arrays.util.ArrayUtil;

public class ArrayMergeTask {

  private Integer[] firstArray;
  private Integer[] secondArray;

  public void setArrays(Integer[] firstArray, Integer[] secondArray) {
    this.firstArray = Arrays.copyOf(firstArray, firstArray.length);
    this.secondArray = Arrays.copyOf(secondArray, secondArray.length);
  }

  public Integer[] mergeArrayWithoutResultArraySorting() {
    ArrayUtil.validateArrayIsNotNull(firstArray);
    ArrayUtil.validateArrayIsNotNull(secondArray);
    sortArrays();
    return ArrayUtil.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(firstArray, secondArray);
  }

  private void sortArrays() {
    Arrays.sort(secondArray);
    Arrays.sort(firstArray);
  }
}
