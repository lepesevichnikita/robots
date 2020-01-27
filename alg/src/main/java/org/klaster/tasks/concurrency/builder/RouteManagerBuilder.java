/*
 * RouteManagerBuilder
 *
 * practice
 *
 * 12:10
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.builder;

import java.util.LinkedList;
import java.util.List;
import org.klaster.tasks.concurrency.model.BusStop;
import org.klaster.tasks.concurrency.service.RouteManager;

public class RouteManagerBuilder {

  private Integer passengersLoadTimeByMilliseconds;
  private Integer passengersExitTimeByMilliseconds;
  private List<BusStop> route;

  public RouteManagerBuilder() {
    reset();
  }

  public void reset() {
    passengersExitTimeByMilliseconds = 0;
    passengersLoadTimeByMilliseconds = 0;
    route = new LinkedList<>();
  }

  public RouteManagerBuilder setPassengersLoadTimeByMilliseconds(Integer passengersLoadTimeByMilliseconds) {
    this.passengersLoadTimeByMilliseconds = passengersLoadTimeByMilliseconds;
    return this;
  }

  public RouteManagerBuilder setPassengersExitTimeByMilliseconds(Integer passengersExitTimeByMilliseconds) {
    this.passengersExitTimeByMilliseconds = passengersExitTimeByMilliseconds;
    return this;
  }

  public RouteManagerBuilder setRoute(List<BusStop> route) {
    this.route = route;
    return this;
  }

  public RouteManager getRouteManager() {
    RouteManager routeManager = new RouteManager();
    routeManager.setPassengersLoadTimeByMilliseconds(passengersLoadTimeByMilliseconds);
    routeManager.setPassengersExitTimeByMilliseconds(passengersExitTimeByMilliseconds);
    routeManager.setRoute(route);
    return routeManager;
  }
}
