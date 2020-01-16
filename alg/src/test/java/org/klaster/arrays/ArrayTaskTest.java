/*
 * ArrayTaskTest
 *
 * practice
 *
 * 12:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArrayTaskTest {

  @DataProvider(name = "intArrays")
  public static Object[][] intArrays() {
    return new Object[][] {
        {new Integer[]{1, 2, 3, 4, 5}, new Integer[]{5, 4, 3, 2, 1}},
        {new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{6, 5, 4, 3, 2, 1}},
        {new Integer[]{}, new Integer[]{}},
        {new Integer[]{1, 1, 1, 1}, new Integer[]{1, 1, 1, 1}},
        {new Integer[]{null, null, null, null}, new Integer[]{null, null, null, null}},
        {new Integer[]{null, 1, null, 2}, new Integer[]{2, null, 1, null}},
        {new Integer[]{1}, new Integer[]{1}}
    };
  }

  @Test(dataProvider = "intArrays")
  public void reverseAllItemsInIntArray(Integer[] sourceArray, Integer[] expectedArray) {
    ArrayTask<Integer> arrayTask = new ArrayTask<>();
    arrayTask.setArray(sourceArray);
    arrayTask.reverseArray();
    assertThat(arrayTask.getArray(), equalTo(expectedArray));
  }
}
