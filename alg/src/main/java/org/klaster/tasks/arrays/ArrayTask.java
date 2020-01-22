/*
 * ArrayTask
 *
 * practice
 *
 * 11:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays;

import java.util.stream.IntStream;

/**
 * Class to reverse array without additional array using
 */
public class ArrayTask<T> {

  private T[] array;

  public T[] getArray() {
    return array;
  }

  public void setArray(T[] array) {
    this.array = array;
  }

  public void reverseArrayWithoutCreatingSupportingArrays() {
    int arrayLength = array.length;
    int endPosition = arrayLength / 2;
    IntStream.range(0, endPosition)
             .forEach((int currentPosition) -> {
               int swappedItemPosition = arrayLength - currentPosition - 1;
               T tempItem = array[currentPosition];
               array[currentPosition] = array[swappedItemPosition];
               array[swappedItemPosition] = tempItem;
             });
  }
}
