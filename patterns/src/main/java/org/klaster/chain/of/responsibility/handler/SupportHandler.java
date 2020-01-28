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

public interface SupportHandler {

  String handleRequest();

  void setNextHandler(SupportHandler supportHandler);

}
