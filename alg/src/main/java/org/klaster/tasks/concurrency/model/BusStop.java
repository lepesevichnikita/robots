/*
 * BusStopBuilder
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusStop {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private final int busesLimit;
  private final List<Bus> currentBuses;
  private final int exitingPassengersCount;
  private final int enteringPassengersCount;

  public BusStop(int busesLimit, int exitingPassengersCount, int enteringPassengersCount) {
    this.busesLimit = busesLimit;
    currentBuses = Collections.synchronizedList(new LinkedList<>());
    this.exitingPassengersCount = exitingPassengersCount;
    this.enteringPassengersCount = enteringPassengersCount;
  }

  public Integer getCurrentBusesCount() {
    return currentBuses.size();
  }

  public boolean isFull() {
    return currentBuses.size() >= busesLimit;
  }

  public void letInBus(Bus bus) throws InterruptedException {
    synchronized (currentBuses) {
      while (isFull()) {
        logger.log(Level.INFO, "BusStopBuilder#{0} is full", this.hashCode());
        currentBuses.wait();
      }
      currentBuses.add(bus);
      logger.log(Level.INFO,
                 "Bus#{0} arrived at BusStopBuilder#{1}, current buses count: {2}",
                 new Object[]{bus.hashCode(), this.hashCode(), getCurrentBusesCount()});
    }
  }

  public void removeBus(Bus bus) {
    synchronized (currentBuses) {
      currentBuses.remove(bus);
      currentBuses.notifyAll();
      logger.log(Level.INFO, "Bus#{0} leaved BusStopBuilder#{1}", new Object[]{bus.hashCode(), this.hashCode()});
    }
  }

  public Integer getExitingPassengersCount() {
    return exitingPassengersCount;
  }

  public Integer getEnteringPassengersCount() {
    return enteringPassengersCount;
  }

}
