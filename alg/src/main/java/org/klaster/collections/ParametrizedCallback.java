/*
 * org.klaster.collections
 *
 * robots
 *
 * 1/15/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.collections;

public interface ParametrizedCallback<T> {
    void execute(T param);
}