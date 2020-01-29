/*
 * GeneralSupportHandler
 *
 * practice
 *
 * 8:58
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chainofresponsibility.handler;

import org.klaster.chainofresponsibility.model.SupportRequest;

public class GeneralSupportHandler extends AbstractSupportHandler {

  @Override
  public String handleRequest(SupportRequest supportRequest) {
    if (supportRequest == SupportRequest.GENERAL) {
      return "General support response";
    }
    return super.handleRequest(supportRequest);
  }
}
