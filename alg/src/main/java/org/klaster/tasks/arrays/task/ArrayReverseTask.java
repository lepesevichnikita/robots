/*
 * ArrayReverseTask
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
public class ArrayReverseTask<T> {

  private T[] array;

  public void setArray(T[] array) {
    this.array = array;
  }

  public T[] reverseArrayWithoutCreatingSupportingArrays() {
    ArrayUtil.validateArrayIsNotNull(array);
    return ArrayUtil.reverseArrayWithoutCreatingSupportingArrays(array);
  }
}
