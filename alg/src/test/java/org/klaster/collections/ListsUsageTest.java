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
import java.util.logging.Logger;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ListsUsageTest {
  private final static int ITEMS_COUNT = 10000;
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
  public void arrayListIsNotDequeue() {
    assertThat(new ArrayList<>(), not(instanceOf(Deque.class)));
  }

  @Test
  public void linkedListWritesFasterThanArrayList() {
    assertThat(ListsUsage.measureListAddingSpeed(new LinkedList<Long>(), ITEMS_COUNT),
        lessThan(ListsUsage.measureListAddingSpeed(new ArrayList<Long>(), ITEMS_COUNT)));
  }
}