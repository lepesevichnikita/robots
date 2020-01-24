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

import java.security.SecureRandom;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class CommonUtils {

  private static final Random random = new SecureRandom();

  private CommonUtils() {
  }

  public static Long getRandomNumber(Long minimum, Long maximum) {
    return random.longs(minimum, maximum)
                 .findAny()
                 .orElse(Math.abs(maximum - minimum) / 2);
  }

  public static Integer getRandomNumber(Integer minimum, Integer maximum) {
    return getRandomNumber(minimum.longValue(), maximum.longValue()).intValue();
  }

  public static Integer getRandomNumber(Integer maximum) {
    return getRandomNumber(Integer.MIN_VALUE, maximum);
  }

  public static Long measureMethod(DefaultCallback callback) {
    final Instant startTime = Clock.systemUTC()
                                   .instant();
    callback.execute();
    final Instant finishTime = Clock.systemUTC()
                                    .instant();
    return Duration.between(startTime, finishTime)
                   .toNanos();
  }
}
