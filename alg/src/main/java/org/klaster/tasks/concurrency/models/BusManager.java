/* * BusManager
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

public class BusManager {

  private Integer passengersLoadTimeByMilliseconds = 0;
  private Integer passengersExitTimeByMilliseconds = 0;
  private List<BusDriver> busDrivers;
  private List<BusStop> route;
  private ExecutorService threadPool;

  public BusManager() {
    route = new LinkedList<>();
    busDrivers = new LinkedList<>();
    threadPool = Executors.newCachedThreadPool();
  }

  public void runAllBuses() {
    busDrivers.forEach(threadPool::execute);
  }

  public void addBus(Bus bus) {
    BusDriver busDriver = getFirstOrCreateIdleBusDriver();
    busDriver.setBus(bus);
  }

  public void setPassengersLoadTimeByMilliseconds(Integer passengersLoadTimeByMilliseconds) {
    this.passengersLoadTimeByMilliseconds = passengersLoadTimeByMilliseconds;
  }

  public void setPassengersExitTimeByMilliseconds(Integer passengersExitTimeByMilliseconds) {
    this.passengersExitTimeByMilliseconds = passengersExitTimeByMilliseconds;
  }

  public List<BusStop> getRoute() {
    return route;
  }

  public void setRoute(List<BusStop> route) {
    this.route = route;
  }

  public List<BusDriver> getBusDrivers() {
    return busDrivers;
  }

  private BusDriver getFirstOrCreateIdleBusDriver() {
    return busDrivers.stream()
                     .filter(BusDriver::isIdle)
                     .findFirst()
                     .orElseGet(this::createNewIdleBusDriver);
  }

  private BusDriver createNewIdleBusDriver() {
    BusDriver newIdleBusDriver = new BusDriver();
    newIdleBusDriver.setRoute(route);
    newIdleBusDriver.setPassengersExitTimeByMilliseconds(passengersExitTimeByMilliseconds);
    newIdleBusDriver.setPassengersLoadTimeByMilliseconds(passengersLoadTimeByMilliseconds);
    busDrivers.add(newIdleBusDriver);
    return newIdleBusDriver;
  }


}
