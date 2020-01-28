/*
 * ConcurrencyUtil
 *
 * practice
 *
 * 12:25
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.util;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class ConcurrencyUtil {

  private ConcurrencyUtil() {
  }

  @SafeVarargs
  public static <T> void validateListsHasEqualSizes(List<T>... lists) {
    boolean doesntHaveEqualSizes = Arrays.stream(lists)
                                         .anyMatch(currentList -> Arrays.stream(lists)
                                                                        .anyMatch(otherList -> otherList.size() != currentList.size()));
    if (doesntHaveEqualSizes) {
      throw new InvalidParameterException("Lists should have equal size");
    }
  }

}
