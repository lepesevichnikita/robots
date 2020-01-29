/*
 * BillingSupportHandler
 *
 * practice
 *
 * 9:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chain.of.responsibility.handler;

import org.klaster.chain.of.responsibility.model.SupportRequest;

public class BillingSupportHandler extends AbstractSupportHandler {

  @Override
  public String handleRequest(SupportRequest supportRequest) {
    if (supportRequest == SupportRequest.BILLING) {
      return "Billing support response";
    }
    return super.handleRequest(supportRequest);
  }
}
