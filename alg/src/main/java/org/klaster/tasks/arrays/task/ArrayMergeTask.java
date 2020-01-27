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
  private Integer[] mergedArray;

  public void setFirstArray(Integer[] firstArray) {
    this.firstArray = firstArray;
    Arrays.sort(firstArray);
  }

  public void setSecondArray(Integer[] secondArray) {
    this.secondArray = secondArray;
    Arrays.sort(secondArray);
  }

  public Integer[] mergeArrayWithoutResultArraySorting() {
    ArrayUtil.validateArrayIsNotNull(firstArray, "First array must be not null");
    ArrayUtil.validateArrayIsNotNull(secondArray, "Second array must be not null");
    return ArrayUtil.mergeTwoArraysInOneSortedArrayWithoutSortingResultArray(firstArray, secondArray);
  }
}
