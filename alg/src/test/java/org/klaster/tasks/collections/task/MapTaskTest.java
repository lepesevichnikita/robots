/*
 * MapTaskTest
 *
 * practice
 *
 * 10:32
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.collections.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.List;
import org.klaster.tasks.collections.tasks.MapTask;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MapTaskTest {

  private static final Long ITEMS_COUNT = 100000L;
  private MapTask mapTask;

  @BeforeClass
  public void initialize() {
    mapTask = new MapTask(ITEMS_COUNT);
  }

  @Test
  public void linkedHashMapKeepsInsertionOrder() {
    final List<String> linkedHashMapInsertionOrders = mapTask
        .getInsertionOrdersBeforeAndAfterLinkerHashMapChange();
    assertThat(linkedHashMapInsertionOrders.get(0), equalTo(linkedHashMapInsertionOrders.get(1)));
  }

  @Test
  public void hashMapDoesntKeepInsertionOrder() {
    final List<String> hashMapInsertionOrders = mapTask
        .getInsertionOrdersBeforeAndAfterHashMapChange();
    assertThat(hashMapInsertionOrders.get(0), not(equalTo(hashMapInsertionOrders.get(1))));
  }
}