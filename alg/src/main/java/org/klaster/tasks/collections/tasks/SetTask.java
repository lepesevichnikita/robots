/*
 * SetTask
 *
 * practice
 *
 * 11:44
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.tasks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.klaster.tasks.collections.util.SetUtils;

public class SetTask {

  private final Long itemsCount;

  public SetTask(Long itemsCount) {
    this.itemsCount = itemsCount;
  }

  public boolean addSameItemIntoHashSet() {
    final Set<Long> set = new HashSet<>();
    SetUtils.fillSet(set, itemsCount);
    final Long randomNumberFromSet = SetUtils.getRandomItemFromSet(set);
    return set.add(randomNumberFromSet);
  }

  public boolean addNonSameItemIntoHashSet() {
    final Set<Long> set = new HashSet<>();
    SetUtils.fillSet(set, itemsCount);
    final Long uniqueItem = SetUtils.getRandomItemFromSet(set);
    set.remove(uniqueItem);
    return set.add(uniqueItem);
  }

  public List<String> getInsertionOrdersBeforeAndAfterHashSetShaking() {
    return SetUtils.getInsertionOrdersBeforeAndAfterSetInitialization(new HashSet<>(), itemsCount);
  }
}
