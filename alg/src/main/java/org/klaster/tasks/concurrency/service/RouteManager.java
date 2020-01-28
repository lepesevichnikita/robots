/* * RouteManager
 *
 * practice
 *
 * 14:52
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.klaster.tasks.concurrency.model.Bus;
import org.klaster.tasks.concurrency.model.BusStop;

public class RouteManager {

  private Integer passengersLoadTimeByMilliseconds;
  private Integer passengersExitTimeByMilliseconds;
  private List<BusDriver> busDrivers;
  private List<BusStop> route;
  private ExecutorService threadPool;

  public RouteManager() {
    route = new LinkedList<>();
    busDrivers = new LinkedList<>();
    threadPool = Executors.newCachedThreadPool();
    passengersLoadTimeByMilliseconds = 0;
    passengersExitTimeByMilliseconds = 0;
  }

  public void startRoute() {
    getBusDriversOnBuses().forEach(busDriver -> {
      busDriver.setRoute(route);
      threadPool.execute(busDriver);
    });
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

  public void setRoute(List<BusStop> route) {
    this.route = route;
  }

  public List<Integer> getBusesAtBusStopsCounts() {
    return route.stream()
                .map(BusStop::getCurrentBusesCount)
                .collect(Collectors.toList());
  }

  private BusDriver getFirstOrCreateIdleBusDriver() {
    return busDrivers.stream()
                     .filter(BusDriver::isIdle)
                     .findFirst()
                     .orElseGet(this::createNewIdleBusDriver);
  }

  private List<BusDriver> getBusDriversOnBuses() {
    return busDrivers.stream()
                     .filter(busDriver -> !busDriver.isIdle())
                     .collect(Collectors.toList());
  }

  private BusDriver createNewIdleBusDriver() {
    BusDriver newIdleBusDriver = new BusDriver();
    newIdleBusDriver.setPassengersExitTimeByMilliseconds(passengersExitTimeByMilliseconds);
    newIdleBusDriver.setPassengersLoadTimeByMilliseconds(passengersLoadTimeByMilliseconds);
    busDrivers.add(newIdleBusDriver);
    return newIdleBusDriver;
  }

}
