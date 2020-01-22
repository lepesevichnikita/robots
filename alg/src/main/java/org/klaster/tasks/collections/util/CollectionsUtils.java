/*
 * CollectionsUtils
 *
 * practice
 *
 * 17:27
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.klaster.util.CommonUtils;

public class CollectionsUtils {

  public static final Integer MIN_MULTIPLIER = 20;
  public static final Integer MAX_MULTIPLIER = 100;

  private CollectionsUtils() {
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

  public static List<Long> generateValues(Long itemsCount, Long maximumValue, Long minimumValue) {
    return LongStream.generate(() -> CommonUtils.getRandomNumber(maximumValue, minimumValue))
                     .limit(itemsCount)
                     .boxed()
                     .collect(Collectors.toList());
  }

  public static List<Long> generateValues(Long itemsCount) {
    final Long minValue = 0L;
    final Long maxValue = itemsCount;
    return generateValues(itemsCount, maxValue, minValue);
  }

}
