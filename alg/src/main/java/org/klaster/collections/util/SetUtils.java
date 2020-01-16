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

import java.util.Set;
import org.klaster.util.HelperUtils;

public class SetUtils {

  private SetUtils() {
  }

  public static void initializeSet(Set<Long> set, Long itemsNumber) {
    for (Long currentItem = 0L; currentItem < itemsNumber; currentItem++) {
      Long newNumber = HelperUtils.getRandomNumber(itemsNumber * 10, 1);
      while (set.contains(newNumber)) {
        newNumber = HelperUtils.getRandomNumber(itemsNumber * 10, 1);
      }
      set.add(newNumber);
    }
  }

  public static Object getRandomItemFromSet(Set<Long> set) {
    return set.toArray()[Math.toIntExact(HelperUtils.getRandomNumber(set.size(), 0))];
  }

}
