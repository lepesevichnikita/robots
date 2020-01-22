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

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusDriver implements Runnable {

  private static final Logger logger = Logger.getLogger(BusDriver.class.getName());

  private Integer passengersLoadTimeByMilliseconds = 0;
  private Integer passengersExitTimeByMilliseconds = 0;
  private List<BusStop> route;
  private Bus bus;

  public void loadPassengerFromBusStop(BusStop busStop) throws InterruptedException {
    Integer totalEnteredPassengersCount = 0;
    while (!bus.isFull() && totalEnteredPassengersCount < busStop.getEnteringPassengersCount()) {
      bus.loadPassenger();
      totalEnteredPassengersCount++;
      TimeUnit.MILLISECONDS.sleep(passengersLoadTimeByMilliseconds);
    }
  }

  public void dropOffPassengersOnBusStop(BusStop busStop) throws InterruptedException {
    Integer totalExitedPassengerCount = 0;
    while (!bus.isEmpty() && totalExitedPassengerCount < busStop.getExitingPassengersCount()) {
      bus.dropOffPassenger();
      totalExitedPassengerCount++;
      TimeUnit.MILLISECONDS.sleep(passengersExitTimeByMilliseconds);
    }
  }

  @Override
  public void run() {
    route.forEach(busStop -> {
      moveToBusStop(busStop);
      try {
        dropOffPassengersOnBusStop(busStop);
      } catch (InterruptedException e) {
        logger.log(Level.WARNING, "Thread was interrupted during dropping off passengers from bus!", e);
        Thread.currentThread()
              .interrupt();
      }
      try {
        loadPassengerFromBusStop(busStop);
      } catch (InterruptedException e) {
        logger.log(Level.WARNING, "Thread was interrupted during loading passengers from bus stop!", e);
        Thread.currentThread()
              .interrupt();
      }
    });
  }

  public void moveToBusStop(BusStop busStop) {
    if (bus.getCurrentBusStop() != null) {
      leaveBusStop(bus.getCurrentBusStop());
    }
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
    while (busStop.isFull()) {
      logger.info(MessageFormat.format("BusStop#{0} is full", busStop.toString()));
      busStop.letInBus(bus);
    }
    logger.info(MessageFormat.format("Bus#{0} was added to BusStop#{1}", bus.toString(), busStop.toString()));
    bus.setCurrentBusStop(busStop);
  }

  private void leaveBusStop(BusStop busStop) {
    bus.setCurrentBusStop(null);
    busStop.removeBus(bus);
  }
}
