/*
 * ArrayTask
 *
 * practice
 *
 * 11:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.arrays;

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

  public void reverse() {
    int startPosition = 0;
    int arrayLength = array.length;
    int endPosition = arrayLength / 2;
    for (int currentPosition = startPosition; currentPosition < endPosition; currentPosition++) {
      int swappedItemPosition = arrayLength - currentPosition - 1;
      T tempItem = array[currentPosition];
      array[currentPosition] = array[swappedItemPosition];
      array[swappedItemPosition] = tempItem;
    }
  }
}
