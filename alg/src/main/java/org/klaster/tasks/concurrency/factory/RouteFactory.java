/*
 * RouteFactory
 *
 * practice
 *
 * 11:52
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.factory;

import com.sun.javaws.exceptions.InvalidArgumentException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.klaster.tasks.concurrency.builder.BusStopBuilder;
import org.klaster.tasks.concurrency.model.BusStop;
import org.klaster.tasks.concurrency.util.ConcurrencyUtil;

public class RouteFactory {

  private static final RouteFactory routeFactory = new RouteFactory();

  private RouteFactory() {
  }

  public static RouteFactory getInstance() {
    return routeFactory;
  }

  public List<BusStop> createRouteWithSameBusesLimitAtEachBusStop(Integer busStopsCount,
                                                                  Integer busesLimit,
                                                                  Integer enteringPassengersCount,
                                                                  Integer exitingPassengersCount) {
    BusStopBuilder busStopBuilder = new BusStopBuilder();
    busStopBuilder.setBusesLimit(busesLimit)
                  .setEnteringPassengersCount(enteringPassengersCount)
                  .setExitingPassengersCount(exitingPassengersCount);
    return IntStream.range(0, busStopsCount)
                    .mapToObj(currentBusStopNumber -> busStopBuilder.getBusStop())
                    .collect(Collectors.toList());
  }

  public List<BusStop> createCustomRoute(List<Integer> busesLimits,
                                         List<Integer> enteringPassengersCounts,
                                         List<Integer> exitingPassengersCounts) throws InvalidArgumentException {
    BusStopBuilder busStopBuilder = new BusStopBuilder();
    ConcurrencyUtil.validateListsHasEqualSizes(busesLimits, enteringPassengersCounts, exitingPassengersCounts);
    return IntStream.range(0, busesLimits.size())
                    .mapToObj(currentBusStopNumber -> busStopBuilder.setBusesLimit(busesLimits.get(currentBusStopNumber))
                                                                    .setExitingPassengersCount(exitingPassengersCounts.get(currentBusStopNumber))
                                                                    .setExitingPassengersCount(enteringPassengersCounts.get(currentBusStopNumber))
                                                                    .getBusStop())
                    .collect(Collectors.toList());
  }

}
