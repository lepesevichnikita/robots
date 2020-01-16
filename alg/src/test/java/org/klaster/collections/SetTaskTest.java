/*
 * SetTaskTest
 *
 * practice
 *
 * 11:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

public class SetTaskTest {

  private final static Long ITEMS_COUNT = 100000L;
  private final SetTask SET_TASK = new SetTask(ITEMS_COUNT);

  @Test
  public void hashSetStoresOnlyUniqueItems() {
    assertThat(SET_TASK.addNonSameItem(), is(true));
  }

  @Test
  public void hashSetDoesntStoreDuplicates() {
    assertThat(SET_TASK.addSameItemToSet(), is(false));
  }
}