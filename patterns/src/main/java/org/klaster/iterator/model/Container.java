/*
 * Container
 *
 * practice
 *
 * 10:22
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.iterator.model;

public interface Container<T> {

  Iterator<T> getIterator();

  boolean add(T value);
}
