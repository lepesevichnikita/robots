/*
 * PaymentServiceFacade
 *
 * practice
 *
 * 11:17
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.facade.service;

import java.util.LinkedHashSet;
import java.util.Set;
import org.klaster.facade.model.Account;

public class PaymentServiceFacade {

  private final PaymentService paymentService = new PaymentService();

  private final Set<Account> accounts = new LinkedHashSet<>();

  public void addAccount(Account account) {
    accounts.add(account);
  }

  public boolean transferMoney(long sourceAccountId, long targetAccountId, float sum) {
    Account sourceAccount = findAccountById(sourceAccountId);
    Account targetAccount = findAccountById(targetAccountId);
    boolean isPossibleToTransferMoney = sourceAccount != null && targetAccount != null;
    if (isPossibleToTransferMoney) {
      isPossibleToTransferMoney = paymentService.isPossibleToTakeMoney(sourceAccount, sum);
      if (isPossibleToTransferMoney) {
        paymentService.takeMoney(sourceAccount, sum);
        paymentService.addMoney(targetAccount, sum);
      }
    }
    return isPossibleToTransferMoney;
  }

  public float getPaymentServiceBalance() {
    return paymentService.getBalance();
  }

  private Account findAccountById(long id) {
    return accounts.stream()
                   .filter(account -> account.getId() == id)
                   .findFirst()
                   .orElse(null);
  }

}
