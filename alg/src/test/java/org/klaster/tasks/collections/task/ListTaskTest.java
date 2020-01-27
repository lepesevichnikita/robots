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

import org.klaster.tasks.collections.tasks.ListTask;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListTaskTest {

  private static final Long ITEMS_COUNT = 100000L;
  private static final Long INSERTED_ITEMS_COUNT = 50000L;
  private static final Long REMOVED_ITEMS_COUNT = 50000L;
  private static final Long ACCESSES_COUNT = 50000L;
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
}
