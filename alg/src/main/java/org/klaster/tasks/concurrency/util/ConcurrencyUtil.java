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

import com.sun.javaws.exceptions.InvalidArgumentException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConcurrencyUtil {

  private ConcurrencyUtil() {
  }

  @SafeVarargs
  public static <T> void validateListsHasEqualSizes(List<T>... lists) throws InvalidArgumentException {
    boolean doesntHaveEqualSizes = Arrays.stream(lists)
                                         .anyMatch(currentList -> Arrays.stream(lists)
                                                                        .anyMatch(otherList -> otherList.size() != currentList.size()));
    if (doesntHaveEqualSizes) {
      String[] actualListsSizes = Arrays.stream(lists)
                                        .map(list -> MessageFormat.format("List#{0},  size: {1}", list.hashCode(), list.size()))
                                        .collect(Collectors.toList())
                                        .toArray(new String[]{});
      throw new InvalidArgumentException(actualListsSizes);
    }
  }

}
