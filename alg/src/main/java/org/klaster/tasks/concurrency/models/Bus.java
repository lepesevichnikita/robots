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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Bus {

  private final Integer capacity;
  private AtomicInteger currentPassengersCount;
  private Integer passengersLoadingTimeInMinutes;
  private Integer passengersExitTimeInMinutes;
  private BusStop currentBusStop;

  public Bus(BusStop currentBusStop, Integer capacity) {
    this.currentBusStop = currentBusStop;
    this.capacity = capacity;
  }

  public BusStop getCurrentBusStop() {
    return currentBusStop;
  }

  public void setCurrentBusStop(BusStop currentBusStop) {
    this.currentBusStop = currentBusStop;
  }


  public Boolean leaveCurrentBusStop() {
    boolean leaved = currentBusStop != null && currentBusStop.removeBus(this);
    if (leaved) {
      currentBusStop = null;
    }
    return leaved;
  }

  public Boolean enterBusStop(BusStop busStop) {
    boolean entered = currentBusStop == null && busStop.addBus(this);
    if (entered) {
      currentBusStop = busStop;
    }
    return entered;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void addPassenger() throws InterruptedException {
    if (!isFull()) {
      Thread.sleep(TimeUnit.MINUTES.toMillis(passengersExitTimeInMinutes));
      currentPassengersCount.getAndIncrement();
    }
  }

  public void dropOffPassenger() throws InterruptedException {
    if (isEmpty()) {
      Thread.sleep((TimeUnit.MINUTES.toMillis(passengersExitTimeInMinutes)));
      currentPassengersCount.getAndDecrement();
    }
  }

  public Integer getCurrentPassengersCount() {
    return currentPassengersCount.get();
  }

  public Integer getPassengersExitTimeInMinutes() {
    return passengersExitTimeInMinutes;
  }

  public void setPassengersExitTimeInMinutes(Integer passengersExitTimeInMinutes) {
    this.passengersExitTimeInMinutes = passengersExitTimeInMinutes;
  }

  public Integer getPassengersLoadingTimeInMinutes() {
    return passengersLoadingTimeInMinutes;
  }

  public void setPassengersLoadingTimeInMinutes(Integer passengersLoadingTimeInMinutes) {
    this.passengersLoadingTimeInMinutes = passengersLoadingTimeInMinutes;
  }

  public boolean isFull() {
    return capacity.equals(currentPassengersCount.get());
  }

  public boolean isEmpty() {
    return currentPassengersCount.get() == 0;
  }
}
