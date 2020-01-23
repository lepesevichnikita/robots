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
import org.klaster.util.CommonUtils;

public class SetUtils {

  private SetUtils() {
  }

  public static void fillSet(Set<Long> set, Long itemsCount) {
    List<Long> generatedNumbers = CollectionUtils.generateValues(itemsCount, 0L, itemsCount);
    set.addAll(generatedNumbers);
  }

  public static Long getRandomItemFromSet(Set<Long> set) {
    final Integer randomItemIndex = CommonUtils.getRandomNumber(set.size(), 0);
    return new ArrayList<>(set).get(randomItemIndex);
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
    fillSet(cache, size.longValue());
    return cache;
  }

}
