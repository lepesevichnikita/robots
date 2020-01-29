/*
 * TechnicianSupportHandler
 *
 * practice
 *
 * 17:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chainofresponsibility.handler;

import org.klaster.chainofresponsibility.model.SupportRequest;

public class TechnicianSupportHandler extends AbstractSupportHandler {

  @Override
  public String handleRequest(SupportRequest supportRequest) {
    if (supportRequest == SupportRequest.TECHNICIAN) {
      return "Technician support response";
    }
    return super.handleRequest(supportRequest);
  }
}
