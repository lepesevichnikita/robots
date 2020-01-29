/*
 * SupportHandler
 *
 * practice
 *
 * 17:48
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chainofresponsibility.handler;

import org.klaster.chainofresponsibility.model.SupportRequest;

public interface SupportHandler {

  String handleRequest(SupportRequest supportRequest);

  SupportHandler getNextSupportHandler();

  void setNextSupportHandler(SupportHandler nextSupportHandler);

  boolean hasNextSupportHandler();

}
