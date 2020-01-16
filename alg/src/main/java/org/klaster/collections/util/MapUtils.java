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

import static org.klaster.util.HelperUtils.getRandomNumber;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;
import org.klaster.util.HelperUtils;

public class MapUtils {

  private MapUtils() {
  }

  public static void putItemsIntoMap(Map<Long, Long> map, Long itemsNumber) {
    for (Long currentItemKey = 0L; currentItemKey < itemsNumber; currentItemKey++) {
      map.put(getRandomNumber(itemsNumber, currentItemKey), getRandomNumber(itemsNumber, 1L));
    }
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

  public static void shakeMap(Map<Long, Long> map) {
    final Map<Long, Long> cache = createCache(map.size());
    map.putAll(cache);
    cache.forEach(map::remove);
  }

  private static Map<Long, Long> createCache(int size) {
    final Long min = HelperUtils.getMinValueShiftedBySize(size);
    final Long max = HelperUtils.getMaxValueShiftedBySize(size);
    Map<Long, Long> cache = new LinkedHashMap<>();
    LongStream.range(0, size).forEach((long currentItemNumber) -> {
      cache.put(getRandomNumber(max, min), getRandomNumber(max, min));
    });
    return cache;
  }

}
