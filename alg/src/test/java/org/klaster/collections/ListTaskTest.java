/*
 * ListTaskTest
 *
 * practice
 *
 * 14:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.testng.annotations.Test;

public class ListTaskTest {

  private static final Long ITEMS_NUMBER = 10000L;
  private static final ListTask LISTS_TASK = new ListTask(ITEMS_NUMBER);

  @Test
  public void linkedListIsList() {
    assertThat(new LinkedList(), is(instanceOf(List.class)));
  }

  @Test
  public void arrayListIsList() {
    assertThat(new ArrayList<>(), is(instanceOf(List.class)));
  }

  @Test
  public void linkedListIsDeque() {
    assertThat(new LinkedList(), is(instanceOf(Deque.class)));
  }

  @Test
  public void linkedListIsQueue() {
    assertThat(new LinkedList(), is(instanceOf(Queue.class)));
  }

  @Test
  public void arrayListIsNotDeque() {
    assertThat(new ArrayList<>(), not(instanceOf(Deque.class)));
  }

  @Test
  public void linkedListInsertsItemsIntoHeadFasterThanArrayList() {
    assertThat(
        LISTS_TASK.getSpeedOfItemsIntoHeadInsertingByLinkedList(),
        lessThan(LISTS_TASK.getSpeedOfItemsIntoHeadInsertingByArrayList()));
  }

  @Test
  public void arrayListIsFasterAtAccessingItemsFromMiddle() {
    assertThat(
        LISTS_TASK.getSpeedOfAccessToItemFromMiddleOfLinkedList(),
        greaterThan(LISTS_TASK.getSpeedOfAccessToItemFromMiddleOfArrayList()));
  }

  @Test
  public void arrayListIsFasterAtAssigningByIndexFromMiddle() {
    assertThat(
        LISTS_TASK.getSpeedOfAssigningByIndexFromMiddleOfLinkedList(),
        greaterThan(LISTS_TASK.getSpeedOfAssigningByIndexFromMiddleOfArrayList()));
  }

  @Test
  public void linkedListIsFasterAtItemsFromHeadRemoving() {
    assertThat(
        LISTS_TASK.getSpeedOfRemovingFromHeadOfLinkedList(),
        lessThan(LISTS_TASK.getSpeedOfRemovingFromHeadOfArrayList()));
  }
}
