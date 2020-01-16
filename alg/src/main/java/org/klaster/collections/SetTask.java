/*
 * SetTask
 *
 * practice
 *
 * 11:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import java.util.HashSet;
import java.util.Set;
import org.klaster.collections.util.SetUtils;

public class SetTask {

  private final Long itemsCount;

  public SetTask(Long itemsCount) {
    this.itemsCount = itemsCount;
  }

  public boolean addSameItemToSet() {
    final Set<Long> set = new HashSet<>();
    SetUtils.initializeSet(set, itemsCount);
    return set.add((SetUtils.getRandomItemFromSet(set)));
  }

  public boolean addNonSameItem() {
    final Set<Long> set = new HashSet<>();
    SetUtils.initializeSet(set, itemsCount);
    final Long uniqueItem = SetUtils.getRandomItemFromSet(set);
    set.remove(uniqueItem);
    return set.add(uniqueItem);
  }
}
