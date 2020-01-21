/*
 * BusManager
 *
 * practice
 *
 * 14:52
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.models;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.klaster.tasks.concurrency.builder.BusDriverBuilder;

public class BusManager {

  private BusDriverBuilder busDriverBuilder = new BusDriverBuilder();

  private Integer passengersLoadingTimeInSeconds = 0;
  private Integer passengersExitTimeInSecond = 0;
  private List<Bus> buses = new LinkedList<>();
  private List<BusDriver> busDrivers = new LinkedList<>();
  private List<BusStop> route = new LinkedList<>();
  private ExecutorService threadPool = Executors.newCachedThreadPool();

  public void runAllBuses() {
    busDrivers.forEach(threadPool::execute);
  }

  public boolean addBus(Bus bus) {
    BusDriver busDriver = busDriverBuilder.setBus(bus)
                                          .setRoute(route)
                                          .setPassengersExitTimeInSeconds(passengersExitTimeInSecond)
                                          .setPassengersLoadingTimeInSeconds(passengersLoadingTimeInSeconds)
                                          .getBusDriver();
    busDriver.setBus(bus);
    busDriver.setRoute(route);
    busDriver.setPassengersExitTimeInSecond(passengersExitTimeInSecond);
    busDriver.setPassengersLoadingTimeInSeconds(passengersLoadingTimeInSeconds);
    return buses.add(bus);
  }

  public BusDriver getFirstIdleBusDriver() {
    return busDrivers.stream().filter(BusDriver::isIdle).findFirst().orElse(null);
  }

  public Integer getPassengersLoadingTimeInSeconds() {
    return passengersLoadingTimeInSeconds;
  }

  public void setPassengersLoadingTimeInSeconds(Integer passengersLoadingTimeInSeconds) {
    this.passengersLoadingTimeInSeconds = passengersLoadingTimeInSeconds;
  }

  public Integer getPassengersExitTimeInSecond() {
    return passengersExitTimeInSecond;
  }

  public void setPassengersExitTimeInSecond(Integer passengersExitTimeInSecond) {
    this.passengersExitTimeInSecond = passengersExitTimeInSecond;
  }
}
