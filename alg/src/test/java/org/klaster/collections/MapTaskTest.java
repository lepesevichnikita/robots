/*
 * MapTaskTest
 *
 * practice
 *
 * 10:32
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.List;
import org.testng.annotations.Test;

public class MapTaskTest {

  private static final Long ITEMS_NUMBER = 10000L;
  private static final MapTask MAP_TASK = new MapTask(ITEMS_NUMBER);

  @Test
  public void linkedHashMapStoresInsertionOrder() {
    final List<String> linkedHashMapInsertionOrders = MAP_TASK
        .getInsertionOrdersBeforeAndAfterLinkerHashMapChange();
    assertThat(linkedHashMapInsertionOrders.get(0), equalTo(linkedHashMapInsertionOrders.get(1)));
  }

  @Test
  public void hashMapDoesntStoreInsertionOrder() {
    final List<String> hashMapInsertionOrders = MAP_TASK
        .getInsertionOrdersBeforeAndAfterHashMapChange();
    assertThat(hashMapInsertionOrders.get(0), not(equalTo(hashMapInsertionOrders.get(1))));
  }
}