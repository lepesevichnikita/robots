/*
 * PaymentServiceFacadeTest
 *
 * practice
 *
 * 11:26
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.facade.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.klaster.facade.model.Account;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentServiceFacadeTest {

  private PaymentServiceFacade paymentServiceFacade;

  @BeforeMethod
  public void initialize() {
    paymentServiceFacade = new PaymentServiceFacade();
  }

  @Test
  public void transfersMoneyBetweenTwoAccount() {
    Account sourceAccount = new Account(1, 100);
    Account targetAccount = new Account(2, 200);
    paymentServiceFacade.addAccount(sourceAccount);
    paymentServiceFacade.addAccount(targetAccount);
    assertThat(paymentServiceFacade.transferMoney(1, 2, 100), is(true));
  }

  @Test
  public void takesMoneyFromSourceAccount() {
    Account sourceAccount = new Account(1, 100);
    Account targetAccount = new Account(2, 200);
    paymentServiceFacade.addAccount(sourceAccount);
    paymentServiceFacade.addAccount(targetAccount);
    paymentServiceFacade.transferMoney(1, 2, 100);
    final float expectedBalanceOfSourceAccount = 0;
    assertThat(sourceAccount.getBalance(), equalTo(expectedBalanceOfSourceAccount));
  }

  @Test
  public void addsMoneyToTargetAccount() {
    Account sourceAccount = new Account(1, 100);
    Account targetAccount = new Account(2, 200);
    paymentServiceFacade.addAccount(sourceAccount);
    paymentServiceFacade.addAccount(targetAccount);
    paymentServiceFacade.transferMoney(1, 2, 100);
    final float expectedBalanceOfTargetAccount = 300;
    assertThat(targetAccount.getBalance(), equalTo(expectedBalanceOfTargetAccount));
  }

  @Test
  public void balanceOfPaymentServiceIsZero() {
    Account sourceAccount = new Account(1, 100);
    Account targetAccount = new Account(2, 200);
    paymentServiceFacade.addAccount(sourceAccount);
    paymentServiceFacade.addAccount(targetAccount);
    paymentServiceFacade.transferMoney(1, 2, 100);
    final float expectedBalanceOfPaymentService = 0;
    assertThat(paymentServiceFacade.getPaymentServiceBalance(), equalTo(expectedBalanceOfPaymentService));
  }

  @Test
  public void doesntTransferMoneyIfAccountDoesntExist() {
    Account targetAccount = new Account(2, 200);
    paymentServiceFacade.addAccount(targetAccount);
    assertThat(paymentServiceFacade.transferMoney(1, 2, 100), is(false));
  }

  @Test
  public void doesntTransferMoneyIfBalanceIsLessThanSum() {

    Account sourceAccount = new Account(1, 50);
    Account targetAccount = new Account(2, 200);
    paymentServiceFacade.addAccount(sourceAccount);
    paymentServiceFacade.addAccount(targetAccount);
    assertThat(paymentServiceFacade.transferMoney(1, 2, 100), is(false));
  }
}