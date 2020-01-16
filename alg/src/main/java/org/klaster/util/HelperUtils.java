/*
 * HelperUtils
 *
 * practice
 *
 * 16:17
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import org.klaster.collections.model.DefaultCallback;

public class HelperUtils {

  public static final int MIN_MULTIPLIER = 20;
  public static final int MAX_MULTIPLIER = 100;


  private HelperUtils() {
  }

  public static Long getRandomNumber(Long max, Long min) {
    return Math.round(Math.random() * max + min);
  }

  public static Long measureMethod(DefaultCallback callback) {
    final Instant startTime = Clock.systemUTC().instant();
    callback.execute();
    final Instant finishTime = Clock.systemUTC().instant();
    return Duration.between(startTime, finishTime).toNanos();
  }

  public static Long getMaxValueShiftedBySize(int size) {
    return getMinValueShiftedBySize(size) * MAX_MULTIPLIER;
  }

  public static Long getMaxValueShiftedBySize(Long size) {
    return getMaxValueShiftedBySize(Math.toIntExact(size));
  }

  public static Long getMinValueShiftedBySize(int size) {
    return castPrimitiveIntToLong(size * MIN_MULTIPLIER);
  }

  public static Long getMinValueShiftedBySize(Long size) {
    return getMinValueShiftedBySize(Math.toIntExact(size));
  }

  public static Long castPrimitiveIntToLong(int number) {
    return Long.valueOf(number);
  }
}
