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
}
