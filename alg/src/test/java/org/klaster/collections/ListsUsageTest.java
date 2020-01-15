/*
 * ListsUsageTest
 *
 * practice
 *
 * 14:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ListsUsageTest {
  private final static int ITEMS_NUMBER = 10000;
  private final static Logger LOGGER = Logger.getLogger(ListsUsageTest.class.getName());

  @Test
  public void linkedListIsList() {
    assertThat(new LinkedList(), is(instanceOf(List.class)));
  }

  @Test
  public void arrayListIsList() {
    assertThat(new ArrayList<>(), is(instanceOf(List.class)));
  }

  @Test
  public void linkedListIsDequeue() {
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
  public void linkedListAppendsItemsSlowerThanArrayList() {
    assertThat(ListsUsage.measureListAddingSpeed(new LinkedList<Long>(), ITEMS_NUMBER),
        greaterThan(ListsUsage.measureListAddingSpeed(new ArrayList<Long>(), ITEMS_NUMBER)));
  }

  @Test
  public void iterationOverLinkedListIsSlowerThanOverArrayList() {
    assertThat(ListsUsage.measureListReadingSpeed(new LinkedList<>(), ITEMS_NUMBER,
        (Long item) -> LOGGER.info("Item from LinkedList: " + item)),
        greaterThan(ListsUsage.measureListReadingSpeed(new ArrayList<>(), ITEMS_NUMBER,
            (Long item) -> LOGGER.info("Item from ArrayList: " + item)))
    );
  }
}