/*
 * ListTaskTest
 *
 * practice
 *
 * 14:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListTaskTest {

  public static final Long ITEMS_COUNT = 100000L;
  private ListTask listTask;

  @BeforeClass
  public void initialize() {
    listTask = new ListTask(ITEMS_COUNT);
  }

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
        listTask.getTimeOfInsertingItemsIntoHeadByLinkedList(),
        lessThan(listTask.getTimeOfInsertingItemsIntoHeadByArrayList()));
  }

  @Test
  public void arrayListIsFasterAtAccessingItemsFromMiddle() {
    assertThat(
        listTask.getTimeOfAccessToItemFromMiddleOfLinkedList(),
        greaterThan(listTask.getTimeOfAccessToItemFromMiddleOfArrayList()));
  }

  @Test
  public void arrayListIsFasterAtInsertingIntoMiddle() {
    assertThat(
        listTask.getTimeOfAssigningByIndexFromMiddleOfLinkedList(),
        greaterThan(listTask.getTimeOfAssigningByIndexFromMiddleOfArrayList()));
  }

  @Test
  public void linkedListIsFasterAtItemsFromHeadRemoving() {
    assertThat(
        listTask.getTimeOfRemovingFromHeadOfLinkedList(),
        lessThan(listTask.getTimeOfRemovingFromHeadOfArrayList()));
  }
}
