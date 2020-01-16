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

public class SetUtils {

  private SetUtils() {
  }

  public static void initializeSet(Set<Long> set, Long itemsNumber) {
    for (Long currentItem = 0L; currentItem < itemsNumber; currentItem++) {
      Long newNumber = Helper.getRandomNumber(itemsNumber * 10, 1);
      while (set.contains(newNumber)) {
        newNumber = Helper.getRandomNumber(itemsNumber * 10, 1);
      }
      set.add(newNumber);
    }
  }

  public static Object getRandomItemFromSet(Set<Long> set) {
    return set.toArray()[Math.toIntExact(Helper.getRandomNumber(set.size(), 0))];
  }

}
