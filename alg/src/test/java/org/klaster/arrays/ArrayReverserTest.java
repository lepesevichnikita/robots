/*
 * ArrayReverserTest
 *
 * practice
 *
 * 12:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ArrayReverserTest {

  @DataProvider(name = "intArrays")
  public static Object[][] intArrays() {
    return new Object[][] {
        {new Integer[] {1, 2, 3, 4, 5}, new Integer[] {5, 4, 3, 2, 1}},
        {new Integer[] {1, 2, 3, 4, 5, 6}, new Integer[] {6, 5, 4, 3, 2, 1}},
        {new Integer[] {}, new Integer[] {}},
        {new Integer[] {1, }, new Integer[] {}},
        {new Integer[] {1}, new Integer[] {1}}
    };
  }

  @Test(dataProvider = "intArrays")
  public void reverseAllItemsInIntArray(Integer[] sourceArray, Integer[] expectedArray) {
    ArrayReverser<Integer> arrayReverser = new ArrayReverser<>();
    arrayReverser.setArray(sourceArray);
    arrayReverser.reverse();
    assertThat(arrayReverser.getArray(), equalTo(expectedArray));
  }
}