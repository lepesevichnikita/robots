/*
 * ArrayTaskTest
 *
 * practice
 *
 * 12:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArrayTaskTest {

  private ArrayTask<Integer> arrayTask;

  @DataProvider(name = "intArrays")
  public static Object[][] intArrays() {
    return new Object[][]{
        {new Integer[]{1, 2, 3, 4, 5}, new Integer[]{5, 4, 3, 2, 1}},
        {new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{6, 5, 4, 3, 2, 1}},
        {new Integer[]{}, new Integer[]{}},
        {new Integer[]{1, 1, 1, 1}, new Integer[]{1, 1, 1, 1}},
        {new Integer[]{null, null, null, null}, new Integer[]{null, null, null, null}},
        {new Integer[]{null, 1, null, 2}, new Integer[]{2, null, 1, null}},
        {new Integer[]{1}, new Integer[]{1}}
    };
  }

  @BeforeClass
  public void initialize() {
    arrayTask = new ArrayTask<>();
  }

  @Test(dataProvider = "intArrays")
  public void reverseAllArrayItems(Integer[] sourceArray, Integer[] expectedArray) {
    arrayTask.setArray(sourceArray);
    arrayTask.reverseArrayWithoutCreatingSupportingArrays();
    assertThat(arrayTask.getArray(), equalTo(expectedArray));
  }

  @Test
  public void acceptsEmptyArrays() {
    Integer[] emptyArray = new Integer[]{};
    arrayTask.setArray(emptyArray);
    arrayTask.reverseArrayWithoutCreatingSupportingArrays();
    assertThat(arrayTask.getArray(), equalTo(emptyArray));
  }

  @Test
  public void reversesArrayWithNulls() {
    Integer[] sourceArray = new Integer[]{null, 1, null, 2};
    Integer[] expectedResult = new Integer[]{2, null, 1, null};
    arrayTask.setArray(sourceArray);
    arrayTask.reverseArrayWithoutCreatingSupportingArrays();
    assertThat(arrayTask.getArray(), equalTo(expectedResult));
  }

  @Test
  public void acceptsArraysOfNulls() {
    Integer[] sourceArray = new Integer[]{null, null, null, null};
    arrayTask.setArray(sourceArray);
    arrayTask.reverseArrayWithoutCreatingSupportingArrays();
    assertThat(arrayTask.getArray(), equalTo(sourceArray));
  }
}
