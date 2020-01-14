/*
 * ListsUsage
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.second_task;

import java.util.List;

/**
 * This class allows to get difference between LinkedList and ArrayList
 */
public class ListsUsage {
  public static long measureListAddingSpeed(List<Long> list, final long itemsNumber) {
    return measureMethod(() -> addItemsToList(list, itemsNumber));
  }

  private static List<Long> addItemsToList(List<Long> list, Long itemsNumber) {
    for (Long currentIndex = 0L; currentIndex < itemsNumber; currentIndex ++) {
      list.add(currentIndex);
    }
    return list;
  }

  public static long measureMethod(Callback callback) {
    final long measuringStartTime = System.nanoTime();
    callback.execute();
    final long meausredTime = System.nanoTime() - measuringStartTime;
    return meausredTime;
  }
}
