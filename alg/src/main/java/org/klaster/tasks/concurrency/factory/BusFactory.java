/*
 * BusFactory
 *
 * practice
 *
 * 12:48
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.factory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.klaster.tasks.concurrency.model.Bus;

public class BusFactory {

  private static final BusFactory busFactory = new BusFactory();

  private BusFactory() {
  }

  public static BusFactory getInstance() {
    return busFactory;
  }

  public List<Bus> createBusesWithSameCapacity(Integer busesCount, Integer busesCapacity) {
    return IntStream.range(0, busesCount)
                    .mapToObj(currentBusNumber -> new Bus(busesCapacity))
                    .collect(Collectors.toList());
  }
}
