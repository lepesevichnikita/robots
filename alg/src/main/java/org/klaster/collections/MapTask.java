/*
 * MapTask
 *
 * practice
 *
 * 9:54
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.klaster.collections.util.MapUtils;

public class MapTask {

  private final Long itemsNumber;

  public MapTask(Long itemsNumber) {
    this.itemsNumber = itemsNumber;
  }

  public List<String> getInsertionOrdersBeforeAndAfterHashMapChange() {
    return MapUtils.getInsertionOrdersBeforeAndAfterMapChange(new HashMap<>(), getItemsNumber());
  }

  public List<String> getInsertionOrdersBeforeAndAfterLinkerHashMapChange() {
    return MapUtils
        .getInsertionOrdersBeforeAndAfterMapChange(new LinkedHashMap<>(), getItemsNumber());
  }

  public Long getItemsNumber() {
    return itemsNumber;
  }
}
