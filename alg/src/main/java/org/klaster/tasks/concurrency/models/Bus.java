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

public class Bus {

  private final Integer capacity;
  private Integer currentPassengersCount;
  private BusStop currentBusStop;

  public Bus(Integer capacity) {
    this.capacity = capacity;
    currentPassengersCount = 0;
  }

  public void loadPassenger() {
    if (!isFull()) {
      currentPassengersCount++;
    }
  }

  public void dropOffPassenger() {
    if (!isEmpty()) {
      currentPassengersCount--;
    }
  }

  public Integer getCurrentPassengersCount() {
    return currentPassengersCount;
  }

  public boolean isEmpty() {
    return currentPassengersCount == 0;
  }

  public boolean isFull() {
    return currentPassengersCount >= capacity;
  }

  public BusStop getCurrentBusStop() {
    return currentBusStop;
  }

  public void setCurrentBusStop(BusStop currentBusStop) {
    this.currentBusStop = currentBusStop;
  }
}
