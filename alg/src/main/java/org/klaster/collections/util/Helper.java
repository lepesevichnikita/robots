/*
 * Helper
 *
 * practice
 *
 * 16:17
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections.util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import org.klaster.collections.module.DefaultCallback;

public class Helper {

  private Helper() {
  }

  public static Long getRandomNumber(long max, long min) {
    return (long) (Math.random() * max + min);
  }

  public static Long measureMethod(DefaultCallback callback) {
    final Instant startTime = Clock.systemUTC().instant();
    callback.execute();
    final Instant finishTime = Clock.systemUTC().instant();
    return Duration.between(startTime, finishTime).toNanos();
  }
}
