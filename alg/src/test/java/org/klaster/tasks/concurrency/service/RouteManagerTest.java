/*
 * RouteManagerTest
 *
 * practice
 *
 * 9:37
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.klaster.tasks.concurrency.builder.RouteManagerBuilder;
import org.klaster.tasks.concurrency.factory.BusFactory;
import org.klaster.tasks.concurrency.factory.RouteFactory;
import org.klaster.tasks.concurrency.model.Bus;
import org.klaster.tasks.concurrency.model.BusStop;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RouteManagerTest {

  private RouteManager routeManager;
  private RouteManagerBuilder routeManagerBuilder;

  @BeforeMethod
  public void initialize() {
    routeManagerBuilder = new RouteManagerBuilder();
    routeManager = routeManagerBuilder.getRouteManager();
  }

  @DataProvider
  public Object[][] dataForRouterManager() {
    return new Object[][]{
        {RouteFactory.getInstance().createRouteWithSameBusesLimitAtEachBusStop(3, 2, 45, 45),
            BusFactory.getInstance().createBusesWithSameCapacity(3, 100), 10, 10, 300, new Integer[]{2, 0, 0}},
        {RouteFactory.getInstance().createRouteWithSameBusesLimitAtEachBusStop(5, 0, 10, 10),
            BusFactory.getInstance().createBusesWithSameCapacity(10, 100), 0, 0, 100, new Integer[]{0, 0, 0, 0, 0}},
        {RouteFactory.getInstance().createRouteWithSameBusesLimitAtEachBusStop(5, 10, 100, 100),
            BusFactory.getInstance().createBusesWithSameCapacity(10, 0), 100, 10, 500, new Integer[]{0, 0, 0, 0, 10}},
        {RouteFactory.getInstance().createCustomRoute(Arrays.asList(0, 0, 1, 0, 0), Arrays.asList(0, 0, 50, 0, 0), Arrays.asList(0, 0, 50, 0, 0)),
            BusFactory.getInstance().createBusesWithSameCapacity(1, 150), 10, 10, 500, new Integer[]{0, 0, 0, 0, 0}},
        {RouteFactory.getInstance().createRouteWithSameBusesLimitAtEachBusStop(1, 0, 0, 0),
            BusFactory.getInstance().createBusesWithSameCapacity(5, 10), 0, 0, 10, new Integer[]{0}}
    };
  }

  @Test(dataProvider = "dataForRouterManager")
  public void busStopDoesntLetInBusIfIsFull(List<BusStop> route,
                                            List<Bus> buses,
                                            Integer passengersLoadTimeByMilliseconds,
                                            Integer passengersExitTimeByMilliseconds,
                                            Integer testSleepTimeByMilliseconds,
                                            Integer[] expectedBusesAtBusStopsCounts) throws InterruptedException {
    routeManager.setRoute(route);
    routeManager.setPassengersExitTimeByMilliseconds(passengersExitTimeByMilliseconds);
    routeManager.setPassengersLoadTimeByMilliseconds(passengersLoadTimeByMilliseconds);
    buses.forEach(routeManager::addBus);
    routeManager.startRoute();
    TimeUnit.MILLISECONDS.sleep(testSleepTimeByMilliseconds);
    List<Integer> busesAtBusStopsCounts = routeManager.getBusesAtBusStopsCounts();
    assertThat(busesAtBusStopsCounts, contains(expectedBusesAtBusStopsCounts));
  }

}