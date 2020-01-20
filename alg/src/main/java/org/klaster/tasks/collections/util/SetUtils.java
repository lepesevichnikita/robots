/*
 * SetUtils
 *
 * practice
 *
 * 11:20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;
import org.klaster.util.CommonUtils;

public class SetUtils {

  private SetUtils() {
  }

  public static void initializeSet(Set<Long> set, Long itemsCount) {
    LongStream.range(0, itemsCount).forEach(currentItem -> {
      final Long maxValue = itemsCount;
      final Long minValue = 0L;
      Long randomNumber = CommonUtils.getRandomNumber(maxValue, minValue);
      while (set.contains(randomNumber)) {
        randomNumber = CommonUtils.getRandomNumber(maxValue, minValue);
      }
      set.add(randomNumber);
    });
  }

  public static Long getRandomItemFromSet(Set<Long> set) {
    final int randomItemFromSetIndex = Math.toIntExact(
        CommonUtils.getRandomNumber(Long.valueOf(set.size()), 0L));
    return new ArrayList<Long>(set).get(randomItemFromSetIndex);
  }

  public static List<String> getInsertionOrdersBeforeAndAfterSetInitialization(Set<Long> set, Long itemsCount) {
    final List<String> insertionOrders = new LinkedList<>();
    final Set<Long> cache = createCash(itemsCount.intValue());
    insertionOrders.add(cache.toString());
    set.addAll(cache);
    insertionOrders.add(set.toString());
    return insertionOrders;
  }

  private static Set<Long> createCash(Integer size) {
    Set<Long> cache = new LinkedHashSet<>();
    initializeSet(cache, size.longValue());
    return cache;
  }

}
