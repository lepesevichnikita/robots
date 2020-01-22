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

  public synchronized Integer getCurrentBusesCount() {
    return currentBuses.size();
  }

  public synchronized boolean isFull() {
    return currentBuses.size() > busesLimit;
  }

  public synchronized void letInBus(Bus bus) {
    if (!isFull()) {
      currentBuses.add(bus);
    }
  }

  public synchronized void removeBus(Bus bus) {
    currentBuses.remove(bus);
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
