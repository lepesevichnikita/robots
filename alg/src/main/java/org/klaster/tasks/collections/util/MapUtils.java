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
import java.util.stream.LongStream;

public class MapUtils {

  private MapUtils() {
  }

  public static void initializeMap(Map<Long, Long> map, Long itemsNumber) {
    LongStream.range(0, itemsNumber).boxed().forEach(
        currentItemKey -> {
          final Long randomKey = getRandomNumber(itemsNumber, currentItemKey);
          final Long randomValue = getRandomNumber(itemsNumber, 1L);
          map.put(randomKey, randomValue);
        });
  }

  public static List<String> getInsertionOrdersBeforeAndAfterMapChange(Map<Long, Long> map,
                                                                       Long itemsNumber) {
    final List<String> insertionOrders = new LinkedList<>();
    initializeMap(map, itemsNumber);
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
    final Long minValue = CollectionsUtils.getMinValueShiftedBySize(size);
    final Long maxValue = CollectionsUtils.getMaxValueShiftedBySize(size);
    Map<Long, Long> cache = new LinkedHashMap<>();
    CollectionsUtils.generateValues(size.longValue(), maxValue, minValue).forEach(randomKey -> {
      final Long randomValue = getRandomNumber(maxValue, minValue);
      cache.put(randomKey, randomValue);
    });
    return cache;
  }

}
