/*
 * ListsUsage
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import java.util.List;

/**
 * This class allows to get difference between LinkedList and ArrayList
 */
public class ListsUsage {
  private ListsUsage() {
  }

  public static long measureListAddingSpeed(List<Long> list, long itemsNumber) {
    return measureMethod(() -> addItemsIntoEndList(list, itemsNumber));
  }

  public static long measureListReadingSpeed(List<Long> list, long itemsNumber,
      ParametrizedCallback<Long> callback) {
    addItemsIntoEndList(list, itemsNumber);
    return measureMethod(() -> readItems(list, callback));
  }

  private static List<Long> addItemsIntoEndList(List<Long> list, Long itemsNumber) {
    for (Long currentIndex = 0L; currentIndex < itemsNumber; currentIndex++) {
      list.add(currentIndex);
    }
    return list;
  }

  private static <T> void readItems(List<T> list, ParametrizedCallback<T> callback) {
    for (T item : list) {
      callback.execute(item);
    }
  }

  public static long measureMethod(DefaultCallback callback) {
    final long measuringStartTime = System.nanoTime();
    callback.execute();
    return System.nanoTime() - measuringStartTime;
  }
}
