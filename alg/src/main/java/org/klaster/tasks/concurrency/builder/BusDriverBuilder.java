/*
 * BusDriverBuilder
 *
 * practice
 *
 * 17:33
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.builder;

import java.util.LinkedList;
import java.util.List;
import org.klaster.tasks.concurrency.models.Bus;
import org.klaster.tasks.concurrency.models.BusDriver;
import org.klaster.tasks.concurrency.models.BusStop;

public class BusDriverBuilder {

  private Bus bus;
  private List<BusStop> route;
  private Integer passengersExitTimeInSeconds;
  private Integer passengersLoadingTimeInSeconds;

  public BusDriverBuilder() {
    reset();
  }

  public BusDriverBuilder setPassengersExitTimeInSeconds(Integer passengersExitTimeInSecond) {
    this.passengersExitTimeInSeconds = passengersExitTimeInSecond;
    return this;
  }

  public BusDriverBuilder setPassengersLoadingTimeInSeconds(Integer passengersLoadingTimeInSecond) {
    this.passengersLoadingTimeInSeconds = passengersLoadingTimeInSecond;
    return this;
  }

  public BusDriverBuilder setRoute(List<BusStop> route) {
    this.route = route;
    return this;
  }

  public BusDriverBuilder setBus(Bus bus) {
    this.bus = bus;
    return this;
  }

  public BusDriver getBusDriver() {
    BusDriver busDriver = createBusDriver();
    reset();
    return busDriver;
  }

  public void reset() {
    route = new LinkedList<>(route);
    bus = new Bus(bus);
  }

  private BusDriver createBusDriver() {
    BusDriver busDriver = new BusDriver();
    busDriver.setBus(bus);
    busDriver.setRoute(route);
    busDriver.setPassengersExitTimeInSecond(passengersExitTimeInSeconds);
    busDriver.setPassengersLoadingTimeInSeconds(passengersLoadingTimeInSeconds);
    return busDriver;
  }
}
