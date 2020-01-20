/*
 * CommonUtils
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

public class CommonUtils {

  private CommonUtils() {
  }

  public static Long getRandomNumber(Long max, Long min) {
    return Math.round(Math.random() * max + min);
  }

  public static Integer getRandomNumber(Integer max, Integer min) {
    return Math.toIntExact(getRandomNumber(max.longValue(), min.longValue()));
  }

  public static Long measureMethod(DefaultCallback callback) {
    final Instant startTime = Clock.systemUTC().instant();
    callback.execute();
    final Instant finishTime = Clock.systemUTC().instant();
    return Duration.between(startTime, finishTime).toNanos();
  }
}
