/*
 * BusStopBuilder
 *
 * practice
 *
 * 11:41
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.builder;

import org.klaster.tasks.concurrency.model.BusStop;

public class BusStopBuilder {

  private Integer busesLimit;
  private Integer exitingPassengersCount;
  private Integer enteringPassengersCount;

  public BusStopBuilder() {
    reset();
  }

  public void reset() {
    busesLimit = 0;
    exitingPassengersCount = Integer.MAX_VALUE;
    enteringPassengersCount = 0;
  }

  public BusStopBuilder setBusesLimit(Integer busesLimit) {
    this.busesLimit = busesLimit;
    return this;
  }

  public BusStopBuilder setExitingPassengersCount(Integer exitingPassengersCount) {
    this.exitingPassengersCount = exitingPassengersCount;
    return this;
  }

  public BusStopBuilder setEnteringPassengersCount(Integer enteringPassengersCount) {
    this.enteringPassengersCount = enteringPassengersCount;
    return this;
  }

  public BusStop getBusStop() {
    return new BusStop(busesLimit, exitingPassengersCount, enteringPassengersCount);
  }
}
