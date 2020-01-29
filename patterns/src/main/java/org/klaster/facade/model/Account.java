/*
 * Account
 *
 * practice
 *
 * 10:55
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.facade.model;

public class Account {

  private final long id;
  private float income;
  private float outcome;

  public Account(long id, float startIncome) {
    this.id = id;
    this.income = startIncome;
  }

  public float getBalance() {
    return income - outcome;
  }

  public void addOutcome(float sum) {
    outcome += sum;
  }

  public void addIncome(float sum) {
    income += sum;
  }

  public long getId() {
    return id;
  }
}
