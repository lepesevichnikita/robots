/*
 * ListUtils
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.util;

import java.util.List;
import java.util.stream.LongStream;
import org.klaster.util.CommonUtils;

/**
 * This class allows to get difference between LinkedList and ArrayList
 */
public class ListUtils {

  private ListUtils() {
  }

  public static Long measureInsertingItemsIntoHead(List<Long> list, Long itemsCount) {
    return CommonUtils.measureMethod(() -> insertItemsIntoHeadOfList(list, itemsCount));
  }

  public static Long measureRemovingFromHeadOfList(List<Long> list, Long itemsCount) {
    appendItemsToList(list, itemsCount);
    return CommonUtils.measureMethod(
        () -> {
          while (!list.isEmpty()) {
            list.remove(0);
          }
        });
  }

  public static Long measureAccessToItemFromMiddleOfList(List<Long> list,
                                                         Long itemsCount) {
    appendItemsToList(list, itemsCount);
    return CommonUtils.measureMethod(
        () -> {
          LongStream.range(0, itemsCount).boxed()
                    .forEach(currentItemIndex -> list.get((currentItemIndex.intValue() / 2)));
        });
  }

  public static Long measureAssigningByIndexFromMiddle(List<Long> list, Long itemsCount) {
    appendItemsToList(list, itemsCount);
    Long baseMiddleIndex = itemsCount / 2;
    return CommonUtils.measureMethod(
        () -> LongStream.range(0, itemsCount / 2).forEach(currentItemIndex -> {
          final Long minValue = CollectionsUtils.getMinValueShiftedBySize(itemsCount);
          final Long maxValue = CollectionsUtils.getMaxValueShiftedBySize(itemsCount);
          final Long randomNumber = CommonUtils.getRandomNumber(maxValue, minValue);
          final Long currentMiddleIndex = baseMiddleIndex + currentItemIndex / 2;
          list.add(currentMiddleIndex.intValue(), randomNumber);
        }));
  }

  private static void appendItemsToList(List<Long> list, Long itemsCount) {
    LongStream.range(0, itemsCount).forEach(currentItemNumber -> {
      final Long minValue = CollectionsUtils.getMinValueShiftedBySize(itemsCount);
      final Long maxValue = CollectionsUtils.getMaxValueShiftedBySize(itemsCount);
      final Long randomNumber = CommonUtils.getRandomNumber(maxValue, minValue);
      list.add(randomNumber);
    });
  }

  private static void insertItemsIntoHeadOfList(List<Long> list, Long itemsCount) {
    LongStream.range(0, itemsCount).forEach((long currentItemNumber) -> list
        .add(0, CommonUtils.getRandomNumber(CollectionsUtils.getMaxValueShiftedBySize(itemsCount),
            CollectionsUtils.getMinValueShiftedBySize(itemsCount))));
  }
}
