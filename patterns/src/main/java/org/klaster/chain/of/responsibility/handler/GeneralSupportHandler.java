/*
 * GeneralSupportHandler
 *
 * practice
 *
 * 8:58
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chain.of.responsibility.handler;

import org.klaster.chain.of.responsibility.model.SupportRequest;

public class GeneralSupportHandler extends AbstractSupportHandler {

  @Override
  public String handleRequest(SupportRequest supportRequest) {
    if (supportRequest == SupportRequest.GENERAL) {
      return "General support response";
    }
    return super.handleRequest(supportRequest);
  }
}
