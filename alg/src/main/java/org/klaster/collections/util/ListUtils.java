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

import java.util.List;
import java.util.stream.LongStream;
import org.klaster.collections.model.ParametrizedCallback;
import org.klaster.util.HelperUtils;

/**
 * This class allows to get difference between LinkedList and ArrayList
 */
public class ListUtils {

  private ListUtils() {
  }

  public static Long measureItemsIntoHeadOfListInserting(List<Long> list, Long itemsCount) {
    return HelperUtils.measureMethod(() -> insertItemsIntoHeadOfList(list, itemsCount));
  }

  public static Long measureRemovingFromHeadOfList(List<Long> list, Long itemsCount) {
    appendItemsToList(list, itemsCount);
    return HelperUtils.measureMethod(
        () -> {
          while (!list.isEmpty()) {
            list.remove(0);
          }
        });
  }

  private static void appendItemsToList(List<Long> list, Long itemsCount) {
    LongStream.range(0, itemsCount).forEach((long currentItemNumber) -> list
        .add(HelperUtils.getRandomNumber(HelperUtils.getMaxValueShiftedBySize(itemsCount),
            HelperUtils.getMinValueShiftedBySize(itemsCount))));
  }

  private static void insertItemsIntoHeadOfList(List<Long> list, Long itemsCount) {
    LongStream.range(0, itemsCount).forEach((long currentItemNumber) -> list
        .add(0, HelperUtils.getRandomNumber(HelperUtils.getMaxValueShiftedBySize(itemsCount),
            HelperUtils.getMinValueShiftedBySize(itemsCount))));
  }

  public static Long measureAccessToItemFromMiddleOfList(List<Long> list,
                                                         Long itemsCount,
                                                         ParametrizedCallback<Long> callback) {
    appendItemsToList(list, itemsCount);
    return HelperUtils.measureMethod(
        () -> LongStream.range(itemsCount / 2, itemsCount).forEach(
            (long currentItemIndex) -> callback
                .execute(list.get(Math.toIntExact(currentItemIndex)))));
  }

  public static Long measureAssigningByIndexFromMiddle(List<Long> list, Long itemsCount) {
    appendItemsToList(list, itemsCount);
    return HelperUtils.measureMethod(
        () -> LongStream.range(itemsCount / 2, itemsCount)
            .forEach((long currentItemIndex) -> list.add(Math.toIntExact(currentItemIndex),
                HelperUtils.getRandomNumber(HelperUtils.getMaxValueShiftedBySize(itemsCount),
                    HelperUtils.getMinValueShiftedBySize(itemsCount)))));
  }
}
