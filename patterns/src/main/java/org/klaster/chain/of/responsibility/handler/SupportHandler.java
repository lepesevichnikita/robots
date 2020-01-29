/*
 * SupportHandler
 *
 * practice
 *
 * 17:48
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chain.of.responsibility.handler;

import org.klaster.chain.of.responsibility.model.SupportRequest;

public interface SupportHandler {

  String handleRequest(SupportRequest supportRequest);

  SupportHandler getNextSupportHandler();

  void setNextSupportHandler(SupportHandler nextSupportHandler);

  boolean hasNextSupportHandler();

}
