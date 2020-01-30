/*
 * ListContainerTest
 *
 * practice
 *
 * 10:31
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.iterator.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListContainerTest {

  private Container<Integer> listContainer;

  @BeforeMethod
  public void initialize() {
    listContainer = new ListContainer<>();
  }

  @Test
  public void iteratorHasNoNextValueForEmptyContainer() {
    Iterator<Integer> iterator = listContainer.getIterator();
    assertThat(iterator.hasNext(), is(false));
  }

  @Test
  public void iteratorHasNextValueIfContainerIsNotEmpty() {
    final Integer value = 10;
    listContainer.add(value);
    Iterator<Integer> iterator = listContainer.getIterator();
    assertThat(iterator.hasNext(), is(true));
  }

  @Test
  public void iteratorNextValueIsEqualToExpected() {
    final Integer value = 10;
    listContainer.add(value);
    Iterator<Integer> iterator = listContainer.getIterator();
    assertThat(iterator.next(), equalTo(value));
  }

  @Test
  public void iteratorNextValueAfterAccessIsNotEqualToPreviousValue() {
    final Integer firstValue = 10;
    final Integer secondValue = 20;
    listContainer.add(firstValue);
    listContainer.add(secondValue);
    Iterator<Integer> iterator = listContainer.getIterator();
    assertThat(iterator.next(), not(equalTo(iterator.next())));
  }

  @Test
  public void hasNoNextValueAtEndOfContainer() {
    final Integer firstValue = 10;
    final Integer secondValue = 20;
    listContainer.add(firstValue);
    listContainer.add(secondValue);
    Iterator<Integer> iterator = listContainer.getIterator();
    iterator.next();
    iterator.next();
    assertThat(iterator.next(), nullValue());
  }

  @Test
  public void nextValueIsNullAtEndOfContainer() {
    final Integer firstValue = 10;
    final Integer secondValue = 20;
    listContainer.add(firstValue);
    listContainer.add(secondValue);
    Iterator<Integer> iterator = listContainer.getIterator();
    iterator.next();
    iterator.next();
    assertThat(iterator.hasNext(), is(false));
  }
}