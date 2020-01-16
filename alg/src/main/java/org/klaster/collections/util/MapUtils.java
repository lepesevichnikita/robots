/*
 * MapUtils
 *
 * practice
 *
 * 9:37
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections.util;

import static org.klaster.collections.util.Helper.getRandomNumber;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtils {

  private static final int MIN_MULTIPLIER = 20;
  private static final int MAX_MULTIPLIER = 100;

  private MapUtils() {
  }

  public static void putItemsIntoMap(Map<Long, Long> map, Long itemsNumber) {
    for (Long currentItemKey = 0L; currentItemKey < itemsNumber; currentItemKey++) {
      map.put(getRandomNumber(itemsNumber, currentItemKey), getRandomNumber(itemsNumber, 1));
    }
  }

  public static void shakeMap(Map<Long, Long> map) {
    final Map<Long, Long> cache = createCache(map.size());
    map.putAll(cache);
    cache.forEach(map::remove);
  }

  private static Map<Long, Long> createCache(int size) {
    final int min = size * MIN_MULTIPLIER;
    final int max = min * MAX_MULTIPLIER;
    Map<Long, Long> cache = new LinkedHashMap<>();
    for (Long itemNumber = 0L; itemNumber < size; itemNumber++) {
      cache.put(getRandomNumber(max, min), getRandomNumber(max, min));
    }
    return cache;
  }

  public static List<String> getInsertionOrdersBeforeAndAfterMapChange(Map<Long, Long> map,
                                                                       Long itemsNumber) {
    final List<String> insertionOrders = new LinkedList<>();
    putItemsIntoMap(map, itemsNumber);
    insertionOrders.add(map.toString());
    shakeMap(map);
    insertionOrders.add(map.toString());
    return insertionOrders;
  }
}
