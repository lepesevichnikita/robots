/*
 * AbstractSupportHandler
 *
 * practice
 *
 * 17:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chain.of.responsibility.handler;

import org.klaster.chain.of.responsibility.model.SupportRequest;

public class AbstractSupportHandler implements SupportHandler {

  private SupportHandler nextSupportHandler;

  @Override
  public SupportHandler getNextSupportHandler() {
    return nextSupportHandler;
  }

  @Override
  public void setNextSupportHandler(SupportHandler nextSupportHandler) {
    this.nextSupportHandler = nextSupportHandler;
  }

  @Override
  public boolean hasNextSupportHandler() {
    return nextSupportHandler != null;
  }

  @Override
  public String handleRequest(SupportRequest supportRequest) {
    if (nextSupportHandler != null) {
      return nextSupportHandler.handleRequest(supportRequest);
    }
    return "Default response";
  }

}
