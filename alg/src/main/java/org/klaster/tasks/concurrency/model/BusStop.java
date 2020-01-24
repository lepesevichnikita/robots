/*
 * BusStop
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

  private static final Logger logger = Logger.getLogger(BusStop.class.getName());

  private final Integer busesLimit;
  private final List<Bus> currentBuses;
  private Integer exitingPassengersCount;
  private Integer enteringPassengersCount;

  public BusStop(Integer busesLimit) {
    this.busesLimit = busesLimit;
    currentBuses = Collections.synchronizedList(new LinkedList<>());
    exitingPassengersCount = 0;
    enteringPassengersCount = 0;
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
        logger.log(Level.INFO, "BusStop#{0} is full", this.hashCode());
        currentBuses.wait();
      }
      currentBuses.add(bus);
      logger.log(Level.INFO,
          "Bus#{0} arrived at BusStop#{1}, current buses count: {2}",
          new Object[]{bus.hashCode(), this.hashCode(), getCurrentBusesCount()});
    }
  }

  public void removeBus(Bus bus) {
    synchronized (currentBuses) {
      currentBuses.remove(bus);
      currentBuses.notifyAll();
      logger.log(Level.INFO, "Bus#{0} leaved BusStop#{1}", new Object[]{bus.hashCode(), this.hashCode()});
    }
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
