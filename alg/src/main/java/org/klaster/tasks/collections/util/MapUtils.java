/*
 * MapUtils
 *
 * practice
 *
 * 9:37
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.util;

import static org.klaster.util.CommonUtils.getRandomNumber;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtils {

  private MapUtils() {
  }

  public static void fillMap(Map<Long, Long> map, Long itemsCount) {
    CollectionUtils.generateValues(itemsCount, Long.MIN_VALUE, itemsCount)
                   .forEach(currentItemKey -> {
                     final Long randomValue = getRandomNumber(Long.MIN_VALUE, itemsCount);
                     map.put(currentItemKey, randomValue);
                   });
  }

  public static List<String> getInsertionOrdersBeforeAndAfterMapChange(Map<Long, Long> map,
                                                                       Long itemsCount) {
    final List<String> insertionOrders = new LinkedList<>();
    fillMap(map, itemsCount);
    insertionOrders.add(map.toString());
    shakeMap(map);
    insertionOrders.add(map.toString());
    return insertionOrders;
  }

  public static void shakeMap(Map<Long, Long> map) {
    final Map<Long, Long> cache = createCacheForMapShaking(map.size());
    map.putAll(cache);
    cache.forEach(map::remove);
  }

  private static Map<Long, Long> createCacheForMapShaking(Integer size) {
    final Long minimumValue = CollectionUtils.getMinValueShiftedBySize(size);
    final Long maximumValue = CollectionUtils.getMaxValueShiftedBySize(size);
    Map<Long, Long> cache = new LinkedHashMap<>();
    CollectionUtils.generateValues(size.longValue(), minimumValue, maximumValue)
                   .forEach(randomKey -> {
                     final Long randomValue = getRandomNumber(minimumValue, maximumValue);
                     cache.put(randomKey, randomValue);
                   });
    return cache;
  }

}
