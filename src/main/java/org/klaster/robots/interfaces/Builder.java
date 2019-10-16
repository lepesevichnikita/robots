package org.klaster.robots.interfaces;

/**
 * @author Nikita Lepesevich <lepesevich.nikita@yandex.ru> on 10/16/19
 * @project robots
 */
public interface Builder<T> {
    T getResult();

    Builder reset();
}
