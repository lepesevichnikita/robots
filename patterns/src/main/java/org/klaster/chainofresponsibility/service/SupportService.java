/*
 * SupportService
 *
 * practice
 *
 * 9:02
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chainofresponsibility.service;

import org.klaster.chainofresponsibility.handler.SupportHandler;
import org.klaster.chainofresponsibility.model.SupportRequest;

public class SupportService implements SupportHandlerContainer {

  private SupportHandler supportHandler;


  @Override
  public String handleRequest(SupportRequest supportRequest) {
    return supportHandler.handleRequest(supportRequest);
  }

  @Override
  public void addHandler(SupportHandler supportHandler) {
    if (this.supportHandler == null) {
      this.supportHandler = supportHandler;
    } else {
      SupportHandler lastSupportHandler = this.supportHandler;
      while (lastSupportHandler.hasNextSupportHandler()) {
        lastSupportHandler = lastSupportHandler.getNextSupportHandler();
      }
      lastSupportHandler.setNextSupportHandler(supportHandler);
    }
  }

  @Override
  public void clean() {
    supportHandler = null;
  }
}
