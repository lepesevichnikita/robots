/*
 * Container
 *
 * practice
 *
 * 10:22
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.iterator.iterator;

public interface Container<T> {

  Iterator<T> getIterator();

  boolean add(T value);
}
