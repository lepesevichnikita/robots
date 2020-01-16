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

  private final Long itemsNumber;

  public SetTask(Long itemsNumber) {
    this.itemsNumber = itemsNumber;
  }

  public boolean addSameItemToSet() {
    final Set<Long> set = new HashSet<>();
    SetUtils.initializeSet(set, getItemsNumber());
    return set.add((Long) SetUtils.getRandomItemFromSet(set));
  }

  public boolean addNonSameItem() {
    final Set<Long> set = new HashSet<>();
    SetUtils.initializeSet(set, getItemsNumber());
    final Long uniqueItem = (Long) SetUtils.getRandomItemFromSet(set);
    set.remove(uniqueItem);
    return set.add(uniqueItem);
  }

  public Long getItemsNumber() {
    return itemsNumber;
  }
}
