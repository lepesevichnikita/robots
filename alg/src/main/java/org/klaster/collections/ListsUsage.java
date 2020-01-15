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

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * This class allows to get difference between LinkedList and ArrayList
 */
public class ListsUsage {
  private ListsUsage() {
  }

  public static Long measureItemsIntoHeadOfListInserting(List<Long> list, Long itemsNumber) {
    return measureMethod(() -> insertItemsIntoHeadOfList(list, itemsNumber));
  }

  public static Long measureIterationOverList(List<Long> list, Long itemsNumber,
      ParametrizedCallback<Long> callback) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(() -> readItems(list, callback));
  }

  public static Long measureRemovingFromList(List<Long> list, Long itemsNumber) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(() -> {
      while (!list.isEmpty()) {
        list.remove(0);
      }
    });
  }

  private static List<Long> appendItemsToList(List<Long> list, Long itemsNumber) {
    for (Long currentIndex = 0L; currentIndex < itemsNumber; currentIndex++) {
      list.add((int) (long) currentIndex, getRandomNumber(itemsNumber, 1));
    }
    return list;
  }

  private static List<Long> insertItemsIntoHeadOfList(List<Long> list, Long itemsNumber) {
    for (Long currentItemIndex = 0L; currentItemIndex < itemsNumber; currentItemIndex++) {
      list.add(0, getRandomNumber(itemsNumber, 1));
    }
    return list;
  }

  private static <T> void readItems(List<T> list, ParametrizedCallback<T> callback) {
    for (T item : list) {
      callback.execute(item);
    }
  }

  public static Long measureMethod(DefaultCallback callback) {
    final Instant startTime = Clock.systemUTC().instant();
    callback.execute();
    final Instant finishTime = Clock.systemUTC().instant();
    return Duration.between(startTime, finishTime).toNanos();
  }

  private static Long getRandomNumber(long max, long min) {
    return (long) (Math.random() * max + min);
  }

  public static Long measureAccessToItemFromMiddleOfList(List<Long> list, Long itemsNumber,
      ParametrizedCallback<Long> callback) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(() -> {
      for (Long currentItemIndex = itemsNumber / 2; currentItemIndex >= 0; currentItemIndex--) {
        callback.execute(list.get((int) (long) currentItemIndex));
      }
    });
  }

  public static Long measureAssigningByIndexFromMiddle(List<Long> list, Long itemsNumber) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(() -> {
      for (Long currentItemIndex = itemsNumber / 2; currentItemIndex < itemsNumber;
          currentItemIndex++) {
        list.add((int) (long) currentItemIndex, getRandomNumber(itemsNumber, 1));
      }
    });
  }
}
