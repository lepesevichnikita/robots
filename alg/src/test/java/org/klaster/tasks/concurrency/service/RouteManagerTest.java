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
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.klaster.tasks.concurrency.builder.BusStopBuilder;
import org.klaster.tasks.concurrency.builder.RouteManagerBuilder;
import org.klaster.tasks.concurrency.factory.BusFactory;
import org.klaster.tasks.concurrency.factory.RouteFactory;
import org.klaster.tasks.concurrency.model.Bus;
import org.klaster.tasks.concurrency.model.BusStop;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RouteManagerTest {

  private static final Integer PASSENGERS_EXIT_TIME_BY_MILLISECONDS = 10;
  private static final Integer PASSENGERS_LOAD_TIME_BY_MILLISECONDS = 10;
  private static final Integer ENTERING_PASSENGERS_COUNT = 45;
  private static final Integer EXITING_PASSENGERS_COUNT = 45;
  private static final Integer BUSES_LIMIT = 2;
  private static final Integer BUSES_COUNT = 3;
  private static final Integer BUS_STOPS_COUNT = 3;
  private static final Integer BUSES_CAPACITY = 100;
  private RouteManager routeManager;
  private BusStopBuilder busStopBuilder;
  private RouteManagerBuilder routeManagerBuilder;

  @BeforeMethod
  public void initialize() {
    busStopBuilder = new BusStopBuilder();
    routeManagerBuilder = new RouteManagerBuilder();
    List<BusStop> route = RouteFactory.getInstance()
                                      .createRouteWithSameBusesLimitAtEachBusStop(BUS_STOPS_COUNT,
                                                                                  BUSES_LIMIT,
                                                                                  ENTERING_PASSENGERS_COUNT,
                                                                                  EXITING_PASSENGERS_COUNT);
    BusStop busDepot = busStopBuilder.setBusesLimit(BUSES_COUNT)
                                     .setEnteringPassengersCount(0)
                                     .setEnteringPassengersCount(Integer.MAX_VALUE)
                                     .getBusStop();
    route.add(busDepot);
    routeManager = routeManagerBuilder.setRoute(route)
                                      .setPassengersExitTimeByMilliseconds(PASSENGERS_EXIT_TIME_BY_MILLISECONDS)
                                      .setPassengersLoadTimeByMilliseconds(PASSENGERS_LOAD_TIME_BY_MILLISECONDS)
                                      .getRouteManager();
    BusFactory.getInstance()
              .createBusesWithSameCapacity(BUSES_COUNT, BUSES_CAPACITY)
              .forEach(routeManager::addBus);
  }

  @Test
  public void busWaitsUntilThereIsFreeSlotOnBusStop() throws InterruptedException {
    routeManager.startRoute();
    TimeUnit.MILLISECONDS.sleep(400);
    final Long actualWaitingBusesCount = routeManager.getBusDrivers()
                                                     .stream()
                                                     .filter(busDriver -> busDriver.getBus()
                                                                                   .getCurrentBusStop() == null)
                                                     .count();
    final Long expectedWaitingBusesCount = BUSES_LIMIT > BUSES_COUNT
                                           ? 0
                                           : BUSES_COUNT - BUSES_LIMIT.longValue();
    assertThat(actualWaitingBusesCount, equalTo(expectedWaitingBusesCount));
  }

  @Test
  public void hasDefinedCountOfPassengersAtRouteEnd() throws InterruptedException {
    routeManager.startRoute();
    TimeUnit.SECONDS.sleep(4);
    final Integer anyBusAtBusStopPassengersCount = routeManager.getBusDrivers()
                                                               .stream()
                                                               .filter(busDriver -> busDriver.getBus()
                                                                                             .getCurrentBusStop() !=
                                                                                    null)
                                                               .map(BusDriver::getBus)
                                                               .map(Bus::getCurrentPassengersCount)
                                                               .findAny()
                                                               .orElse(0);
    final Integer expectedFinalPassengersCount = 45;
    assertThat(anyBusAtBusStopPassengersCount, equalTo(expectedFinalPassengersCount));
  }

  @Test
  public void loadedBusesLeaveBusStop() throws InterruptedException {
    routeManager.startRoute();
    TimeUnit.MILLISECONDS.sleep(700);
    BusStop firstBusStop = routeManager.getRoute()
                                       .get(0);
    final Integer expectedBusesCountOnFirstBusStop = BUSES_LIMIT > BUSES_COUNT
                                                     ? 0
                                                     : BUSES_COUNT - BUSES_LIMIT;
    assertThat(firstBusStop.getCurrentBusesCount(), equalTo(expectedBusesCountOnFirstBusStop));
  }

  @DataProvider
  public Object[][] dataForRouterManager() {
    return new Object[][]{
        {RouteFactory.getInstance().createRouteWithSameBusesLimitAtEachBusStop(BUS_STOPS_COUNT, BUSES_LIMIT, ENTERING_PASSENGERS_COUNT, EXITING_PASSENGERS_COUNT),
            BusFactory.getInstance().createBusesWithSameCapacity(BUSES_COUNT, BUSES_CAPACITY), PASSENGERS_EXIT_TIME_BY_MILLISECONDS, PASSENGERS_LOAD_TIME_BY_MILLISECONDS, 500,
            new Integer[]{2, 0, 0}}
    }

        ;
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
    assertThat(routeManager.getBusesAtBusStopsCounts(), contains(expectedBusesAtBusStopsCounts));
  }

}