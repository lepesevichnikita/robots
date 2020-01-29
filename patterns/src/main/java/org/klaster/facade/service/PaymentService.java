/*
 * PaymentService
 *
 * practice
 *
 * 11:06
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.facade.service;

import org.klaster.facade.model.Account;

public class PaymentService {

  private float income;
  private float outcome;

  public void takeMoney(Account account, float sum) {
    if (isPossibleToTakeMoney(account, sum)) {
      account.addOutcome(sum);
      income += sum;
    }
  }

  public void addMoney(Account account, float sum) {
    account.addIncome(sum);
    outcome += sum;
  }

  public float getBalance() {
    return income - outcome;
  }

  public boolean isPossibleToTakeMoney(Account account, float sum) {
    return account.getBalance() >= sum;
  }
}
