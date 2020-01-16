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
    final int min = size * 20;
    final int max = min * 100;
    Map<Long, Long> cache = new LinkedHashMap<>();
    for (Long time = 0L; time < size; time++) {
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
