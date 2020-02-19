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
    List<Long> items = CollectionUtils.generateValues(insertedItemsCount, itemsCount, insertedItemsCount + itemsCount);
    return CommonUtils.measureMethod(() -> insertItemsIntoHeadOfList(list, items));
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
    List<Long> items = CollectionUtils.generateValues(insertedItemsCount, itemsCount, insertedItemsCount + itemsCount);
    return CommonUtils.measureMethod(() -> items.forEach(generatedNumber -> list.add(baseMiddleIndex.intValue(), generatedNumber)));
  }

  private static void appendItemsToList(List<Long> list, Long itemsCount) {
    appendItemsToList(list, CollectionUtils.generateValues(itemsCount, 0L, itemsCount));
  }

  private static void appendItemsToList(List<Long> list, List<Long> items) {
    list.addAll(items);
  }

  private static void insertItemsIntoHeadOfList(List<Long> list, List<Long> items) {
    items.forEach(item -> list.add(0, item));
  }

  public static Long measureAppendingToList(List<Long> list, Long itemsCount, Long insertedItemsCount) {
    appendItemsToList(list, itemsCount);
    List<Long> items = CollectionUtils.generateValues(insertedItemsCount, itemsCount, itemsCount + insertedItemsCount);
    return CommonUtils.measureMethod(() -> appendItemsToList(list, items));
  }
}
