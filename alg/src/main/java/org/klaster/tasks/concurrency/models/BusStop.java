/*
 * BusStop
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BusStop {

  private final Integer busesLimit;
  private final List<Bus> currentBuses = Collections.synchronizedList(new LinkedList<>());

  public BusStop(Integer busesLimit) {
    this.busesLimit = busesLimit;
  }

  public boolean addBus(Bus bus) {
    boolean added = false;
    synchronized (currentBuses) {
      if (!isFull()) {
        added = currentBuses.add(bus);
      }
    }
    return added;
  }

  public Boolean removeBus(Bus bus) {
    Boolean removed;
    synchronized (currentBuses) {
      removed = currentBuses.remove(bus);
    }
    return removed;
  }

  private boolean isFull() {
    return busesLimit.equals(currentBuses.size());
  }

  public Integer getBusesLimit() {
    return busesLimit;
  }
}
