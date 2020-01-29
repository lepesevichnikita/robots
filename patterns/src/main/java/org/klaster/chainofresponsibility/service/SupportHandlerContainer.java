/*
 * SupportHandlerContainer
 *
 * practice
 *
 * 9:28
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chainofresponsibility.service;

import org.klaster.chainofresponsibility.handler.SupportHandler;
import org.klaster.chainofresponsibility.model.SupportRequest;

public interface SupportHandlerContainer {

  String handleRequest(SupportRequest supportRequest);

  void addHandler(SupportHandler supportHandler);

  void clean();
}
