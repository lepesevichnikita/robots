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

import org.klaster.chain.of.responsibility.model.Support;

public class AbstractSupportHandler {

  Support support;
  SupportHandler nextHandler;

  AbstractSupportHandler(Support support) {
    this.support = support;
  }

  public void setNextHandler(SupportHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  public String handleRequest() {
    if (nextHandler != null) {
      return nextHandler.handleRequest();
    }
    return "Default response";
  }

}
