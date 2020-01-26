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

  public static Long measureInsertingItemsIntoHead(List<Long> list, Long itemsCount, Long insertedItemsCount) {
    appendItemsToList(list, itemsCount);
    return CommonUtils.measureMethod(() -> insertItemsIntoHeadOfList(list, insertedItemsCount));
  }

  public static Long measureRemovingFromHeadOfList(List<Long> list, Long itemsCount, Long removedItemsCount) {
    appendItemsToList(list, itemsCount);
    final Long correctedRemovedItemsCount = removedItemsCount - (itemsCount % removedItemsCount);
    return CommonUtils
        .measureMethod(() -> LongStream.range(0, correctedRemovedItemsCount)
                                       .forEach(currentRemovedItemNumber -> list.remove(0)));
  }

  public static Long measureAccessToItemFromMiddleOfList(List<Long> list, Long itemsCount, Long accessesCount) {
    appendItemsToList(list, itemsCount);
    return CommonUtils.measureMethod(
        () -> LongStream.range(0, accessesCount)
                        .boxed()
                        .forEach(currentItemIndex -> list.get((currentItemIndex.intValue() / 2))));
  }

  public static Long measureInsertingIntoMiddleOfMiddleOfList(List<Long> list, Long itemsCount, Long insertedItemsCount) {
    appendItemsToList(list, itemsCount);
    final Long baseMiddleIndex = itemsCount / 2;
    return CommonUtils.measureMethod(() -> CollectionUtils.generateValues(insertedItemsCount, 0L, itemsCount)
                                                          .forEach(generatedNumber -> list.add(baseMiddleIndex.intValue(),
                                                                                               generatedNumber)));
  }

  private static void appendItemsToList(List<Long> list, Long itemsCount) {
    list.addAll(CollectionUtils.generateValues(itemsCount, 0L, itemsCount));
  }

  private static void insertItemsIntoHeadOfList(List<Long> list, Long insertedItemsCount) {
    final Long minimumValue = CollectionUtils.getMinValueShiftedBySize(insertedItemsCount);
    final Long maximumValue = CollectionUtils.getMaxValueShiftedBySize(insertedItemsCount);
    LongStream.range(0, insertedItemsCount)
              .forEach(currentItemNumber -> list.add(0, CommonUtils.getRandomNumber(minimumValue, maximumValue)));
  }
}
