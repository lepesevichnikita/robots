/*
 * MapTask
 *
 * practice
 *
 * 9:54
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.tasks;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.klaster.tasks.collections.util.MapUtils;

public class MapTask {

  private final Long itemsCount;

  public MapTask(Long itemsCount) {
    this.itemsCount = itemsCount;
  }

  public List<String> getInsertionOrdersBeforeAndAfterHashMapChange() {
    return MapUtils.getInsertionOrdersBeforeAndAfterMapChange(new HashMap<>(), itemsCount);
  }

  public List<String> getInsertionOrdersBeforeAndAfterLinkerHashMapChange() {
    return MapUtils
        .getInsertionOrdersBeforeAndAfterMapChange(new LinkedHashMap<>(), itemsCount);
  }
}
