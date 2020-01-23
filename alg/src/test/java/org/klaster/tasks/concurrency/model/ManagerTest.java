/*
 * ManagerTest
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerTest {

  private static final Integer PASSENGERS_EXIT_TIME_BY_MILLISECONDS = 10;
  private static final Integer PASSENGERS_LOAD_TIME_BY_MILLISECONDS = 10;
  private static final Integer ENTERING_PASSENGERS_COUNT = 45;
  private static final Integer EXITING_PASSENGERS_COUNT = 45;
  private static final Integer BUSES_LIMIT = 2;
  private static final Integer BUSES_COUNT = 3;
  private static final Integer BUS_STOPS_COUNT = 3;
  private static final Integer BUS_CAPACITY = 100;
  private Manager manager;

  @BeforeMethod
  public void initialize() {
    manager = new Manager();
    manager.setRoute(IntStream.generate(() -> BUSES_LIMIT)
                              .limit(BUS_STOPS_COUNT)
                              .mapToObj(busesLimit -> {
                                BusStop busStop = new BusStop(busesLimit);
                                busStop.setEnteringPassengersCount(ENTERING_PASSENGERS_COUNT);
                                busStop.setExitingPassengersCount(EXITING_PASSENGERS_COUNT);
                                return busStop;
                              })
                              .collect(Collectors.toList()));
    manager.getRoute()
           .add(new BusStop(BUSES_COUNT));
    manager.setPassengersExitTimeByMilliseconds(PASSENGERS_EXIT_TIME_BY_MILLISECONDS);
    manager.setPassengersLoadTimeByMilliseconds(PASSENGERS_LOAD_TIME_BY_MILLISECONDS);
    IntStream.generate(() -> BUS_CAPACITY)
             .limit(BUSES_COUNT)
             .mapToObj(Bus::new)
             .forEach(manager::addBus);
  }

  @Test
  public void busWaitsUntilThereIsFreeSlotOnBusStop() throws InterruptedException {
    manager.runAllBuses();
    TimeUnit.MILLISECONDS.sleep(400);
    final Long actualWaitingBusesCount = manager.getBusDrivers()
                                                .stream()
                                                .filter(busDriver -> busDriver.getBus()
                                                                              .getCurrentBusStop() == null)
                                                .count();
    final Long expectedWaitingBusesCount = BUSES_LIMIT > BUSES_COUNT ? 0 : BUSES_COUNT - BUSES_LIMIT.longValue();
    assertThat(actualWaitingBusesCount, equalTo(expectedWaitingBusesCount));
  }

  @Test
  public void hasDefinedCountOfPassengersAtRouteEnd() throws InterruptedException {
    manager.runAllBuses();
    TimeUnit.SECONDS.sleep(3);
    final Integer anyBusAtBusStopPassengersCount = manager.getBusDrivers()
                                                          .stream()
                                                          .filter(busDriver -> busDriver.getBus()
                                                                                        .getCurrentBusStop() != null)
                                                          .map(BusDriver::getBus)
                                                          .map(Bus::getCurrentPassengersCount)
                                                          .findAny()
                                                          .orElse(0);
    final Integer expectedFinalPassengersCount = 45;
    assertThat(anyBusAtBusStopPassengersCount, equalTo(expectedFinalPassengersCount));
  }

  @Test
  public void loadedBusesLeaveBusStop() throws InterruptedException {
    manager.runAllBuses();
    TimeUnit.MILLISECONDS.sleep(900);
    BusStop firstBusStop = manager.getRoute()
                                  .get(0);
    final Integer expectedBusesCountOnFirstBusStop = BUSES_LIMIT > BUSES_COUNT ? 0 : BUSES_COUNT - BUSES_LIMIT;
    assertThat(firstBusStop.getCurrentBusesCount(), equalTo(expectedBusesCountOnFirstBusStop));
  }

}