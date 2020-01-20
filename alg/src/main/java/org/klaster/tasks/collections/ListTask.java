/*
 * ListTask
 *
 * practice
 *
 * 16:16
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.klaster.tasks.collections.util.ListUtils;

public class ListTask {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private final Long itemsCount;

  public ListTask(Long itemsCount) {
    this.itemsCount = itemsCount;
  }

  public Long getTimeOfInsertingItemsIntoHeadByLinkedList() {
    return ListUtils.measureInsertingItemsIntoHead(new LinkedList<>(), itemsCount);
  }

  public Long getTimeOfInsertingItemsIntoHeadByArrayList() {
    return ListUtils.measureInsertingItemsIntoHead(new ArrayList<>(), itemsCount);
  }

  public Long getTimeOfAccessToItemFromMiddleOfLinkedList() {
    return ListUtils.measureAccessToItemFromMiddleOfList(
        new LinkedList<>(),
        itemsCount,
        (Long item) -> logger.info("Item from LinkedList: " + item));
  }

  public Long getTimeOfAccessToItemFromMiddleOfArrayList() {
    return ListUtils.measureAccessToItemFromMiddleOfList(
        new ArrayList<>(),
        itemsCount,
        (Long item) -> logger.info("Item from ArrayList: " + item));
  }

  public Long getTimeOfAssigningByIndexFromMiddleOfLinkedList() {
    return ListUtils.measureAssigningByIndexFromMiddle(new LinkedList<>(), itemsCount);
  }

  public Long getTimeOfAssigningByIndexFromMiddleOfArrayList() {
    return ListUtils.measureAssigningByIndexFromMiddle(new ArrayList<>(), itemsCount);
  }

  public Long getTimeOfRemovingFromHeadOfLinkedList() {
    return ListUtils.measureRemovingFromHeadOfList(new LinkedList<>(), itemsCount);
  }

  public Long getTimeOfRemovingFromHeadOfArrayList() {
    return ListUtils.measureRemovingFromHeadOfList(new ArrayList<>(), itemsCount);
  }
}
