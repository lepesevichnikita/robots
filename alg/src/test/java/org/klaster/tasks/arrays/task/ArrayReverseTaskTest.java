/*
 * ArrayReverseTaskTest
 *
 * practice
 *
 * 12:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.arrays.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayReverseTaskTest {

  private ArrayReverseTask<Integer> arrayReverseTask;

  @BeforeMethod
  public void initialize() {
    arrayReverseTask = new ArrayReverseTask<>();
  }

  @Test
  public void reverseAllArrayItems() {
    Integer[] sourceArray = new Integer[]{1, 2, 3, 4, 5, 6};
    Integer[] expectedArray = new Integer[]{6, 5, 4, 3, 2, 1};
    arrayReverseTask.setArray(sourceArray);
    assertThat(Arrays.asList(arrayReverseTask.reverseArrayWithoutCreatingSupportingArrays()), contains(expectedArray));
  }

  @Test
  public void acceptsEmptyArrays() {
    Integer[] emptyArray = new Integer[]{};
    arrayReverseTask.setArray(emptyArray);
    assertThat(Arrays.asList(arrayReverseTask.reverseArrayWithoutCreatingSupportingArrays()), empty());
  }

  @Test
  public void reversesArrayWithNulls() {
    Integer[] sourceArray = new Integer[]{null, 1, null, 2};
    Integer[] expectedResult = new Integer[]{2, null, 1, null};
    arrayReverseTask.setArray(sourceArray);
    assertThat(Arrays.asList(arrayReverseTask.reverseArrayWithoutCreatingSupportingArrays()), contains(expectedResult));
  }

  @Test
  public void acceptsArraysOfNulls() {
    Integer[] sourceArray = new Integer[]{null, null, null, null};
    arrayReverseTask.setArray(sourceArray);
    assertThat(Arrays.asList(arrayReverseTask.reverseArrayWithoutCreatingSupportingArrays()), contains(sourceArray));
  }

  @Test
  public void doesntChangeArrayWithSingleItem() {
    Integer[] sourceArray = new Integer[]{1};
    arrayReverseTask.setArray(sourceArray);
    assertThat(arrayReverseTask.reverseArrayWithoutCreatingSupportingArrays(), equalTo(sourceArray));
  }

  @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "Array must be not null")
  public void throwsNullPointerExceptionIfArrayIsNull() {
    arrayReverseTask.reverseArrayWithoutCreatingSupportingArrays();
  }

}
