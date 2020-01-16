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
}
