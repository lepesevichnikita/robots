/*
 * ListTask
 *
 * practice
 *
 * 16:16
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.klaster.collections.util.ListUtils;

public class ListTask {

  private static final Logger LOGGER = Logger.getLogger(ListTask.class.getName());
  private final Long itemsNumber;

  public ListTask(Long itemsNumber) {
    this.itemsNumber = itemsNumber;
  }

  public Long getSpeedOfItemsIntoHeadInsertingByLinkedList() {
    return ListUtils.measureItemsIntoHeadOfListInserting(new LinkedList<>(), getItemsNumber());
  }

  public Long getSpeedOfItemsIntoHeadInsertingByArrayList() {
    return ListUtils.measureItemsIntoHeadOfListInserting(new ArrayList<>(), getItemsNumber());
  }

  public Long getSpeedOfAccessToItemFromMiddleOfLinkedList() {
    return ListUtils.measureAccessToItemFromMiddleOfList(
        new LinkedList<>(),
        getItemsNumber(),
        (Long item) -> LOGGER.info("Item from LinkedList: " + item));
  }

  public Long getSpeedOfAccessToItemFromMiddleOfArrayList() {
    return ListUtils.measureAccessToItemFromMiddleOfList(
        new ArrayList<>(),
        getItemsNumber(),
        (Long item) -> LOGGER.info("Item from ArrayList: " + item));
  }

  public Long getSpeedOfAssigningByIndexFromMiddleOfLinkedList() {
    return ListUtils.measureAssigningByIndexFromMiddle(new LinkedList<>(), getItemsNumber());
  }

  public Long getSpeedOfAssigningByIndexFromMiddleOfArrayList() {
    return ListUtils.measureAssigningByIndexFromMiddle(new ArrayList<>(), getItemsNumber());
  }

  public Long getSpeedOfRemovingFromHeadOfLinkedList() {
    return ListUtils.measureRemovingFromHeadOfList(new LinkedList<>(), getItemsNumber());
  }

  public Long getSpeedOfRemovingFromHeadOfArrayList() {
    return ListUtils.measureRemovingFromHeadOfList(new ArrayList<>(), getItemsNumber());
  }

  public Long getItemsNumber() {
    return itemsNumber;
  }
}
