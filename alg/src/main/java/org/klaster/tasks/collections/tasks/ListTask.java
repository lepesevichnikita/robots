/*
 * ListTask
 *
 * practice
 *
 * 16:16
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import org.klaster.tasks.collections.util.ListUtils;

public class ListTask {

  private final Long itemsCount;

  public ListTask(Long itemsCount) {
    this.itemsCount = itemsCount;
  }

  public Long getTimeOfInsertingItemsIntoHeadByLinkedList(Long insertedItemsCount) {
    return ListUtils.measureInsertingItemsIntoHead(new LinkedList<>(), itemsCount, insertedItemsCount);
  }

  public Long getTimeOfInsertingItemsIntoHeadByArrayList(Long insertedItemsCount) {
    return ListUtils.measureInsertingItemsIntoHead(new ArrayList<>(), itemsCount, insertedItemsCount);
  }

  public Long getTimeOfAccessToItemFromMiddleOfLinkedList(Long accessesCount) {
    return ListUtils.measureAccessToItemFromMiddleOfList(new LinkedList<>(), itemsCount, accessesCount);
  }

  public Long getTimeOfAccessToItemFromMiddleOfArrayList(Long accessesCount) {
    return ListUtils.measureAccessToItemFromMiddleOfList(new ArrayList<>(), itemsCount, accessesCount);
  }

  public Long getTimeOfInsertingIntoMiddleOfLinkedList(Long insertedItemsCount) {
    return ListUtils.measureInsertingIntoMiddleOfMiddleOfList(new LinkedList<>(), itemsCount, insertedItemsCount);
  }

  public Long getTimeOfInsertingIntoMiddleOfArrayList(Long insertedItemsCount) {
    return ListUtils.measureInsertingIntoMiddleOfMiddleOfList(new ArrayList<>(), itemsCount, insertedItemsCount);
  }

  public Long getTimeOfRemovingFromHeadOfLinkedList(Long removedItemsCount) {
    return ListUtils.measureRemovingFromHeadOfList(new LinkedList<>(), itemsCount, removedItemsCount);
  }

  public Long getTimeOfRemovingFromHeadOfArrayList(Long removedItemsCount) {
    return ListUtils.measureRemovingFromHeadOfList(new ArrayList<>(), itemsCount, removedItemsCount);
  }
}
