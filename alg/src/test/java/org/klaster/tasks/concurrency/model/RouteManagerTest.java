/*
 * RouteManagerTest
 *
 * practice
 *
 * 9:37
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.klaster.tasks.concurrency.service.RouteManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RouteManagerTest {

  private static final Integer PASSENGERS_EXIT_TIME_BY_MILLISECONDS = 10;
  private static final Integer PASSENGERS_LOAD_TIME_BY_MILLISECONDS = 10;
  private static final Integer ENTERING_PASSENGERS_COUNT = 45;
  private static final Integer EXITING_PASSENGERS_COUNT = 45;
  private static final Integer BUSES_LIMIT = 2;
  private static final Integer BUSES_COUNT = 3;
  private static final Integer BUS_STOPS_COUNT = 3;
  private static final Integer BUS_CAPACITY = 100;
  private RouteManager routeManager;

  @BeforeMethod
  public void initialize() {
    routeManager = new RouteManager();
    routeManager.setRoute(IntStream.generate(() -> BUSES_LIMIT)
                                   .limit(BUS_STOPS_COUNT)
                                   .mapToObj(busesLimit -> {
                                     BusStop busStop = new BusStop(busesLimit);
                                     busStop.setEnteringPassengersCount(ENTERING_PASSENGERS_COUNT);
                                     busStop.setExitingPassengersCount(EXITING_PASSENGERS_COUNT);
                                     return busStop;
                                   })
                                   .collect(Collectors.toList()));
    routeManager.getRoute()
                .add(new BusStop(BUSES_COUNT));
    routeManager.setPassengersExitTimeByMilliseconds(PASSENGERS_EXIT_TIME_BY_MILLISECONDS);
    routeManager.setPassengersLoadTimeByMilliseconds(PASSENGERS_LOAD_TIME_BY_MILLISECONDS);
    IntStream.generate(() -> BUS_CAPACITY)
             .limit(BUSES_COUNT)
             .mapToObj(Bus::new)
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

}