/*
 * BusDriver
 *
 * practice
 *
 * 14:08
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.klaster.tasks.concurrency.model.Bus;
import org.klaster.tasks.concurrency.model.BusStop;

public class BusDriver implements Runnable {

  private static final Logger logger = Logger.getLogger(BusDriver.class.getName());

  private Integer passengersLoadTimeByMilliseconds = 0;
  private Integer passengersExitTimeByMilliseconds = 0;
  private List<BusStop> route;
  private Bus bus;

  public void loadPassengerFromBusStop(BusStop busStop) {
    Integer totalBoardedPassengers = 0;
    while (!bus.isFull() && totalBoardedPassengers < busStop.getEnteringPassengersCount()) {
      bus.loadPassenger();
      waitUntilPassengerFinishAction(passengersLoadTimeByMilliseconds, "Thread was interrupted during loading passengers in bus!");
      totalBoardedPassengers++;
      logger.log(Level.INFO, "Bus#{0}, current passengers count: {1}",
                 new Object[]{bus.hashCode(), bus.getCurrentPassengersCount()});
    }
  }

  public void dropOffPassengersOnBusStop(BusStop busStop) {
    Integer totalExitedPassengerCount = 0;
    while (!bus.isEmpty() && totalExitedPassengerCount < busStop.getExitingPassengersCount()) {
      bus.dropOffPassenger();
      waitUntilPassengerFinishAction(passengersExitTimeByMilliseconds, "Thread was interrupted during dropping off passengers from bus!");
      totalExitedPassengerCount++;
      logger.log(Level.INFO, "Bus#{0}, current passengers count: {1}",
                 new Object[]{bus.hashCode(), bus.getCurrentPassengersCount()});
    }
  }

  private void waitUntilPassengerFinishAction(Integer passengersExitTimeByMilliseconds, String s) {
    try {
      TimeUnit.MILLISECONDS.sleep(passengersExitTimeByMilliseconds);
    } catch (InterruptedException e) {
      logger.log(Level.WARNING, s, e);
      Thread.currentThread()
            .interrupt();
    }
  }

  @Override
  public void run() {
    route.forEach(busStop -> {
      moveToBusStop(busStop);
      dropOffPassengersOnBusStop(busStop);
      loadPassengerFromBusStop(busStop);
    });
  }

  public void moveToBusStop(BusStop busStop) {
    leaveCurrentBusStop();
    enterBusStop(busStop);
  }

  public void setPassengersExitTimeByMilliseconds(Integer passengersExitTimeByMilliseconds) {
    this.passengersExitTimeByMilliseconds = passengersExitTimeByMilliseconds;
  }

  public void setPassengersLoadTimeByMilliseconds(Integer passengersLoadTimeByMilliseconds) {
    this.passengersLoadTimeByMilliseconds = passengersLoadTimeByMilliseconds;
  }

  public void setRoute(List<BusStop> route) {
    this.route = route;
  }

  public boolean isIdle() {
    return bus == null;
  }

  public Bus getBus() {
    return bus;
  }

  public void setBus(Bus bus) {
    this.bus = bus;
  }

  private void enterBusStop(BusStop busStop) {
    try {
      busStop.letInBus(bus);
      bus.setCurrentBusStop(busStop);
    } catch (InterruptedException e) {
      logger.log(Level.WARNING, "Thread was interrupted during arriving of at bus stop", e);
      Thread.currentThread()
            .interrupt();
    }
  }

  private void leaveCurrentBusStop() {
    if (bus.getCurrentBusStop() != null) {
      bus.getCurrentBusStop()
         .removeBus(bus);
      bus.setCurrentBusStop(null);
    }
  }
}
