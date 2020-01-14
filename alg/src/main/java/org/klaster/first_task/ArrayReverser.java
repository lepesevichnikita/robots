/*
 * ArrayReverser
 *
 * practice
 *
 * 11:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.first_task;

/**
 * Class to reverse array without additional array using
 */
public class ArrayReverser<T> {
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
      int swapItemPosition = arrayLength - currentPosition - 1;
      T temp = array[currentPosition];
      array[currentPosition] = array[swapItemPosition];
      array[swapItemPosition] = temp;
    }
  }
}
