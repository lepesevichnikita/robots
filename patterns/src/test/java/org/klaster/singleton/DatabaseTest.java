/*
 * DatabaseTest
 *
 * practice
 *
 * 14:31
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.singleton;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

import org.testng.annotations.Test;

public class DatabaseTest {

  private static final int THREAD_POOL_SIZE = 4;
  private static final int INVOCATION_COUNT = 10;

  @Test
  public void instanceIsDatabase() {
    assertThat(Database.getInstance(), isA(Database.class));
  }

  @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = INVOCATION_COUNT)
  public void instanceIsSameEveryAccessTime() {
    assertThat(Database.getInstance(), equalTo(Database.getInstance()));
  }

}