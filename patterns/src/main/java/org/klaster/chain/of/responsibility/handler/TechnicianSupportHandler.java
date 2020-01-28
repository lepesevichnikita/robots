/*
 * TechnicianSupportHandler
 *
 * practice
 *
 * 17:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chain.of.responsibility.handler;

import org.klaster.chain.of.responsibility.model.Support;

public class TechnicianSupportHandler extends AbstractSupportHandler {

  TechnicianSupportHandler(Support support) {
    super(support);
  }

  @Override
  public String handleRequest() {
    if (support == Support.TECHNICIAN) {
      return "Technician support response";
    }
    return super.handleRequest();
  }
}
