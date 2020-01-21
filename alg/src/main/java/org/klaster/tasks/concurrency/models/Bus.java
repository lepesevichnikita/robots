/*
 * Bus
 *
 * practice
 *
 * 14:38
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.concurrency.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Bus {

  private final Integer capacity;
  private final AtomicInteger currentPassengersCount = new AtomicInteger(0);
  private BusStop currentBusStop;

  public Bus(Integer capacity) {
    this.capacity = capacity;
  }

  public Bus(Bus bus) {
    this.capacity = bus.capacity;
    this.currentPassengersCount.set(bus.currentPassengersCount.get());
    this.currentBusStop = bus.currentBusStop;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void addPassenger() {
    if (!isFull()) {
      currentPassengersCount.getAndIncrement();
    }
  }

  public void dropOffPassenger() {
    if (!isEmpty()) {
      currentPassengersCount.getAndDecrement();
    }
  }

  public Integer getCurrentPassengersCount() {
    return currentPassengersCount.get();
  }

  public boolean isEmpty() {
    return currentPassengersCount.get() == 0;
  }

  public boolean isFull() {
    return capacity.equals(currentPassengersCount.get());
  }

  public BusStop getCurrentBusStop() {
    return currentBusStop;
  }

  public void setCurrentBusStop(BusStop currentBusStop) {
    this.currentBusStop = currentBusStop;
  }
}
