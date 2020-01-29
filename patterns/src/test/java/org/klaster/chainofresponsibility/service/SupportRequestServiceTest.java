/*
 * SupportRequestServiceTest
 *
 * practice
 *
 * 9:26
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chainofresponsibility.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.klaster.chainofresponsibility.handler.BillingSupportHandler;
import org.klaster.chainofresponsibility.handler.GeneralSupportHandler;
import org.klaster.chainofresponsibility.handler.TechnicianSupportHandler;
import org.klaster.chainofresponsibility.model.SupportRequest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SupportRequestServiceTest {

  private SupportHandlerContainer supportHandlerContainer;

  @BeforeMethod
  public void initialize() {
    supportHandlerContainer = new SupportService();
    supportHandlerContainer.addHandler(new TechnicianSupportHandler());
    supportHandlerContainer.addHandler(new BillingSupportHandler());
    supportHandlerContainer.addHandler(new GeneralSupportHandler());
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionIfThereIsNoSupportHandler() {
    supportHandlerContainer.clean();
    supportHandlerContainer.handleRequest(SupportRequest.UNDEFINED);
  }

  @Test
  public void returnsDefaultResponseIfCorrectSupportHandlerIsNotPassed() {
    final String expectedResponse = "Default response";
    assertThat(supportHandlerContainer.handleRequest(SupportRequest.UNDEFINED), equalTo(expectedResponse));
  }

  @Test
  public void returnsCorrectResponseForTechnicianSupportRequest() {
    final String expectedResponse = "Technician support response";
    assertThat(supportHandlerContainer.handleRequest(SupportRequest.TECHNICIAN), equalTo(expectedResponse));
  }

  @Test
  public void returnsCorrectResponseForBillingSupportRequest() {
    final String expectedResponse = "Billing support response";
    assertThat(supportHandlerContainer.handleRequest(SupportRequest.BILLING), equalTo(expectedResponse));
  }

  @Test
  public void returnsCorrectResponseForGeneralSupportRequest() {
    final String expectedResponse = "General support response";
    assertThat(supportHandlerContainer.handleRequest(SupportRequest.GENERAL), equalTo(expectedResponse));
  }
}