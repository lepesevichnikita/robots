/*
 * SupportHandlerContainer
 *
 * practice
 *
 * 9:28
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.chain.of.responsibility.service;

import org.klaster.chain.of.responsibility.handler.SupportHandler;
import org.klaster.chain.of.responsibility.model.SupportRequest;

public interface SupportHandlerContainer {

  String handleRequest(SupportRequest supportRequest);

  void addHandler(SupportHandler supportHandler);

  void clean();
}
