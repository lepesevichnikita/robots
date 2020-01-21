/*
 * BusDriver
 *
 * practice
 *
 * 14:08
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.models;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BusDriver implements Runnable {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private Integer passengersLoadingTimeInSeconds = 0;
  private Integer passengersExitTimeInSecond = 0;
  private List<BusStop> route;
  private Bus bus;

  public void addPassengerFromBusStop(BusStop busStop) throws InterruptedException {
    Integer totalEnteredPassengersCount = 0;
    while (!bus.isFull() && totalEnteredPassengersCount < busStop.getEnteringPassengersCount()) {
      TimeUnit.SECONDS.sleep(passengersExitTimeInSecond);
      bus.addPassenger();
      totalEnteredPassengersCount++;
    }
  }

  public void dropOffPassengersOnBusStop(BusStop busStop) throws InterruptedException {
    Integer totalExitedPassengerCount = 0;
    while (!bus.isEmpty() && totalExitedPassengerCount < busStop.getExitingPassengersCount()) {
      TimeUnit.SECONDS.sleep(passengersExitTimeInSecond);
      bus.dropOffPassenger();
      totalExitedPassengerCount++;
    }
  }

  @Override
  public void run() {
    route.forEach(busStop -> {
      moveToBusStop(busStop);
      addPassengersInOtherThread(busStop);
      dropOffPassengersInOtherThread(busStop);
    });
  }

  public void moveToBusStop(BusStop busStop) {
    if (bus.getCurrentBusStop() != null) {
      leaveBusStop(bus.getCurrentBusStop());
    }
    try {
      enterBusStop(busStop);
    } catch (InterruptedException e) {
      logger.warning(e.getMessage());
    }
  }

  public Bus getBus() {
    return bus;
  }

  public void setBus(Bus bus) {
    this.bus = bus;
  }

  public Integer getPassengersExitTimeInSecond() {
    return passengersExitTimeInSecond;
  }

  public void setPassengersExitTimeInSecond(Integer passengersExitTimeInSecond) {
    this.passengersExitTimeInSecond = passengersExitTimeInSecond;
  }

  public Integer getPassengersLoadingTimeInSeconds() {
    return passengersLoadingTimeInSeconds;
  }

  public void setPassengersLoadingTimeInSeconds(Integer passengersLoadingTimeInSeconds) {
    this.passengersLoadingTimeInSeconds = passengersLoadingTimeInSeconds;
  }

  public void setRoute(List<BusStop> route) {
    this.route = route;
  }

  public boolean isIdle() {
    return bus == null;
  }

  private boolean enterBusStop(BusStop busStop) throws InterruptedException {
    boolean entered = busStop.addBus(bus);
    while (!entered) {
      entered = busStop.addBus(bus);
      bus.setCurrentBusStop(busStop);
    }
    return entered;
  }

  private boolean leaveBusStop(BusStop busStop) {
    bus.setCurrentBusStop(null);
    return busStop.removeBus(bus);
  }

  private void addPassengersInOtherThread(BusStop busStop) {
    new Thread(() -> {
      try {
        addPassengerFromBusStop(busStop);
      } catch (InterruptedException e) {
        logger.warning(e.getMessage());
      }
    }).start();
  }

  private void dropOffPassengersInOtherThread(BusStop busStop) {
    new Thread(() -> {
      try {
        dropOffPassengersOnBusStop(busStop);
      } catch (InterruptedException e) {
        logger.warning(e.getMessage());
      }
    }).start();
  }

}
