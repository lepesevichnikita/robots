/*
 * CollectionUtils
 *
 * practice
 *
 * 17:27
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CollectionUtils {

  public static final Integer MIN_MULTIPLIER = 20;
  public static final Integer MAX_MULTIPLIER = 100;
  private static final Random random = new Random();

  private CollectionUtils() {
  }

  public static Long getMaxValueShiftedBySize(Integer size) {
    return getMinValueShiftedBySize(size) * MAX_MULTIPLIER;
  }

  public static Long getMaxValueShiftedBySize(Long size) {
    return getMaxValueShiftedBySize(size.intValue());
  }

  public static Long getMinValueShiftedBySize(Integer size) {
    return size * MIN_MULTIPLIER.longValue();
  }

  public static Long getMinValueShiftedBySize(Long size) {
    return getMinValueShiftedBySize(size.intValue());
  }

  public static List<Long> generateValues(Long itemsCount, Long minimum, Long maximum) {
    return random.longs(itemsCount, minimum, maximum)
                 .boxed()
                 .collect(Collectors.toList());
  }

}
