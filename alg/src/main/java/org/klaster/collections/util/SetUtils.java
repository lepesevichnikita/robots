/*
 * SetUtils
 *
 * practice
 *
 * 11:20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections.util;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.LongStream;
import org.klaster.util.HelperUtils;

public class SetUtils {

  private SetUtils() {
  }

  public static void initializeSet(Set<Long> set, Long itemsNumber) {
    LongStream.range(0, itemsNumber).forEach((long currentItem) -> {
      Long newNumber = HelperUtils
          .getRandomNumber(HelperUtils.getMaxValueShiftedBySize(itemsNumber), 1L);
      while (set.contains(newNumber)) {
        newNumber = HelperUtils.getRandomNumber(itemsNumber * 10, 1L);
      }
      set.add(newNumber);
    });
  }

  public static Long getRandomItemFromSet(Set<Long> set) {
    final int randomItemFromSetIndex = Math.toIntExact(
        HelperUtils.getRandomNumber(HelperUtils.castPrimitiveIntToLong(set.size()), 0L));
    return new ArrayList<Long>(set).get(randomItemFromSetIndex);
  }

}
