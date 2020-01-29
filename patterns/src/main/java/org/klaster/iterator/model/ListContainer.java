package org.klaster.iterator.model;/*
 * ListContainer
 *
 * practice
 *
 * 10:25
 *
 * Copyright(c) Nikita Lepesevich
 */

import java.util.ArrayList;
import java.util.List;
import org.klaster.iterator.iterator.Container;
import org.klaster.iterator.iterator.Iterator;

public class ListContainer<T> implements Container<T> {

  private final List<T> list;

  public ListContainer() {
    list = new ArrayList<>();
  }

  @Override
  public Iterator<T> getIterator() {
    return new ListContainerIterator();
  }

  @Override
  public boolean add(T value) {
    return list.add(value);
  }

  private class ListContainerIterator implements Iterator<T> {

    private int index;

    @Override
    public boolean hasNext() {
      return index < list.size();
    }

    @Override
    public T next() {
      return hasNext()
             ? list.get(index++)
             : null;
    }
  }
}
