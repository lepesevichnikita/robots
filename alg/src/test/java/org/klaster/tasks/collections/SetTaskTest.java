/*
 * SetTaskTest
 *
 * practice
 *
 * 11:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.List;
import org.klaster.tasks.collections.tasks.SetTask;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SetTaskTest {

  private final static Long ITEMS_COUNT = 100000L;
  private SetTask setTask;

  @BeforeClass
  public void initialize() {
    setTask = new SetTask(ITEMS_COUNT);
  }

  @Test
  public void hashSetStoresOnlyUniqueItems() {
    assertThat(setTask.addNonSameItemIntoHashSet(), is(Boolean.TRUE));
  }

  @Test
  public void hashSetDoesntStoreDuplicates() {
    assertThat(setTask.addSameItemIntoHashSet(), is(Boolean.FALSE));
  }

  @Test
  public void hashSetDoesntKeepInsertionOrder() {
    final List<String> insertionOrders = setTask.getInsertionOrdersBeforeAndAfterHashSetShaking();
    assertThat(insertionOrders.get(0), not(equalTo(insertionOrders.get(1))));
  }
}