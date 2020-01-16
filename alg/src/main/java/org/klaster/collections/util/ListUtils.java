/*
 * ListUtils
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections.util;

import static org.klaster.util.HelperUtils.getRandomNumber;
import static org.klaster.util.HelperUtils.measureMethod;

import java.util.List;
import org.klaster.collections.model.ParametrizedCallback;

/**
 * This class allows to get difference between LinkedList and ArrayList
 */
public class ListUtils {

  private ListUtils() {
  }

  public static Long measureItemsIntoHeadOfListInserting(List<Long> list, Long itemsNumber) {
    return measureMethod(() -> insertItemsIntoHeadOfList(list, itemsNumber));
  }

  public static Long measureRemovingFromHeadOfList(List<Long> list, Long itemsNumber) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(
        () -> {
          while (!list.isEmpty()) {
            list.remove(0);
          }
        });
  }

  private static void appendItemsToList(List<Long> list, Long itemsNumber) {
    for (Long currentIndex = 0L; currentIndex < itemsNumber; currentIndex++) {
      list.add(Math.toIntExact(currentIndex), getRandomNumber(itemsNumber, 1L));
    }
  }

  private static void insertItemsIntoHeadOfList(List<Long> list, Long itemsNumber) {
    for (Long currentItem = 0L; currentItem < itemsNumber; currentItem++) {
      list.add(0, getRandomNumber(itemsNumber, 1L));
    }
  }

  public static Long measureAccessToItemFromMiddleOfList(List<Long> list,
                                                         Long itemsNumber,
                                                         ParametrizedCallback<Long> callback) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(
        () -> {
          for (Long currentItemIndex = itemsNumber / 2; currentItemIndex < itemsNumber;
              currentItemIndex++) {
            callback.execute(list.get(Math.toIntExact(currentItemIndex)));
          }
        });
  }

  public static Long measureAssigningByIndexFromMiddle(List<Long> list, Long itemsNumber) {
    appendItemsToList(list, itemsNumber);
    return measureMethod(
        () -> {
          for (Long currentItemIndex = itemsNumber / 2;
              currentItemIndex < itemsNumber;
              currentItemIndex++) {
            list.add((int) (long) currentItemIndex, getRandomNumber(itemsNumber, 1L));
          }
        });
  }
}
