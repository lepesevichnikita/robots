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
  private Integer exitingPassengersCount;
  private Integer enteringPassengersCount;

  public BusStop(Integer busesLimit) {
    this.busesLimit = busesLimit;
  }

  public boolean isFull() {
    return busesLimit.equals(currentBuses.size());
  }

  public synchronized boolean addBus(Bus bus) throws InterruptedException {
    boolean added;
    while (isFull()) {
      currentBuses.wait();
    }
    added = currentBuses.add(bus);
    return added;
  }

  public synchronized boolean removeBus(Bus bus) {
    boolean removed;
    synchronized (currentBuses) {
      removed = currentBuses.remove(bus);
    }
    return removed;
  }

  public Integer getExitingPassengersCount() {
    return exitingPassengersCount;
  }

  public void setExitingPassengersCount(Integer exitingPassengersCount) {
    this.exitingPassengersCount = exitingPassengersCount;
  }

  public Integer getEnteringPassengersCount() {
    return enteringPassengersCount;
  }

  public void setEnteringPassengersCount(Integer enteringPassengersCount) {
    this.enteringPassengersCount = enteringPassengersCount;
  }
}
