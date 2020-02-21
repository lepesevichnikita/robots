/*
 * ListTaskTest
 *
 * practice
 *
 * 14:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.klaster.tasks.collections.tasks.ListTask;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListTaskTest {

  private static final Long ITEMS_COUNT = 100000L;
  private static final Long INSERTED_ITEMS_COUNT = 50000L;
  private static final Long REMOVED_ITEMS_COUNT = 50000L;
  private static final Long ACCESSES_COUNT = 50000L;
  private static final Float PERMISSIBLE_AVERAGE_NANOS_PER_ITEM_DELTA = 60f;
  private ListTask listTask;

  @BeforeClass
  public void initialize() {
    listTask = new ListTask(ITEMS_COUNT);
  }

  @Test
  public void linkedListInsertsItemsIntoHeadFasterThanArrayList() {
    assertThat(
        listTask.getTimeOfInsertingItemsIntoHeadByLinkedList(INSERTED_ITEMS_COUNT),
        lessThan(listTask.getTimeOfInsertingItemsIntoHeadByArrayList(INSERTED_ITEMS_COUNT)));
  }

  @Test
  public void arrayListIsFasterAtAccessingItemsFromMiddle() {
    assertThat(
        listTask.getTimeOfAccessToItemFromMiddleOfLinkedList(ACCESSES_COUNT),
        greaterThan(listTask.getTimeOfAccessToItemFromMiddleOfArrayList(ACCESSES_COUNT)));
  }

  @Test
  public void arrayListIsFasterAtInsertingIntoMiddle() {
    assertThat(
        listTask.getTimeOfInsertingIntoMiddleOfLinkedList(INSERTED_ITEMS_COUNT),
        greaterThan(listTask.getTimeOfInsertingIntoMiddleOfArrayList(INSERTED_ITEMS_COUNT)));
  }

  @Test
  public void linkedListIsFasterAtRemovingItemsFromHead() {
    assertThat(
        listTask.getTimeOfRemovingFromHeadOfLinkedList(REMOVED_ITEMS_COUNT),
        lessThan(listTask.getTimeOfRemovingFromHeadOfArrayList(REMOVED_ITEMS_COUNT)));
  }

  @Test
  public void linkedListIsEqualToArrayListAtAppendingItems() {
    final long timeOfAppendingToLinkedList = listTask.getTimeOfAppendingToLinkedList(INSERTED_ITEMS_COUNT);
    final long timeOfAppendingToArrayList = listTask.getTimeOfAppendingToArrayList(INSERTED_ITEMS_COUNT);
    final float actualNanosDelta = Math.abs(timeOfAppendingToLinkedList - timeOfAppendingToArrayList);
    final float actualAverageNanosPerItemDelta = actualNanosDelta / INSERTED_ITEMS_COUNT;
    assertThat(actualAverageNanosPerItemDelta, lessThanOrEqualTo(PERMISSIBLE_AVERAGE_NANOS_PER_ITEM_DELTA));
  }

  @Test
  public void linkedListIsSignificantFasterAtRemovingItemsFromHead() {
    final long timeOfRemovingFromHeadOfLinkedList = listTask.getTimeOfRemovingFromHeadOfLinkedList(REMOVED_ITEMS_COUNT);
    final long timeOfRemovingFromHeadOfArrayList = listTask.getTimeOfRemovingFromHeadOfArrayList(REMOVED_ITEMS_COUNT);
    final float actualNanosDelta = timeOfRemovingFromHeadOfArrayList - timeOfRemovingFromHeadOfLinkedList;
    final float actualAverageNanosPerItemDelta = actualNanosDelta / (REMOVED_ITEMS_COUNT - REMOVED_ITEMS_COUNT % ITEMS_COUNT);
    assertThat(actualAverageNanosPerItemDelta, greaterThan(PERMISSIBLE_AVERAGE_NANOS_PER_ITEM_DELTA));
  }

  @Test
  public void arrayListIsSignificantFasterAtInsertingIntoMiddle() {
    final long timeOfInsertingIntoMiddleOfLinkedList = listTask.getTimeOfInsertingIntoMiddleOfLinkedList(INSERTED_ITEMS_COUNT);
    final long timeOfInsertingIntoMiddleOfArrayList = listTask.getTimeOfInsertingIntoMiddleOfArrayList(INSERTED_ITEMS_COUNT);
    final float actualNanosDelta = timeOfInsertingIntoMiddleOfLinkedList - timeOfInsertingIntoMiddleOfArrayList;
    final float actualAverageNanosPerItemDelta = actualNanosDelta / INSERTED_ITEMS_COUNT;
    assertThat(actualAverageNanosPerItemDelta, greaterThan(PERMISSIBLE_AVERAGE_NANOS_PER_ITEM_DELTA));
  }

  @Test
  public void arrayListIsSignificantFasterAtAccessingItemsFromMiddle() {
    final long timeOfAccessToItemFromMiddleOfLinkedList = listTask.getTimeOfAccessToItemFromMiddleOfLinkedList(ACCESSES_COUNT);
    final long timeOfAccessToItemFromMiddleOfArrayList = listTask.getTimeOfAccessToItemFromMiddleOfArrayList(ACCESSES_COUNT);
    final float actualNanosDelta = timeOfAccessToItemFromMiddleOfLinkedList - timeOfAccessToItemFromMiddleOfArrayList;
    final float actualAverageNanosPerItemDelta = actualNanosDelta / ACCESSES_COUNT;
    assertThat(actualAverageNanosPerItemDelta, greaterThan(PERMISSIBLE_AVERAGE_NANOS_PER_ITEM_DELTA));
  }

  @Test
  public void linkedListInsertsItemsIntoHeadSignificantFasterThanArrayList() {
    final long timeOfInsertingItemsIntoHeadByLinkedList = listTask.getTimeOfInsertingItemsIntoHeadByLinkedList(INSERTED_ITEMS_COUNT);
    final long timeOfInsertingItemsIntoHeadByArrayList = listTask.getTimeOfInsertingItemsIntoHeadByArrayList(INSERTED_ITEMS_COUNT);
    final float actualNanosDelta = timeOfInsertingItemsIntoHeadByArrayList - timeOfInsertingItemsIntoHeadByLinkedList;
    final float actualAverageNanosPerItemDelta = actualNanosDelta / INSERTED_ITEMS_COUNT;
    assertThat(actualAverageNanosPerItemDelta, greaterThan(PERMISSIBLE_AVERAGE_NANOS_PER_ITEM_DELTA));
  }
}
