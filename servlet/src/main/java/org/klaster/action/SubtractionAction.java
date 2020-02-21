package org.klaster.action;/*
 * org.klaster.service
 *
 * robots
 *
 * 2/4/20
 *
 * Copyright(c) Nikita Lepesevich
 */

import javax.servlet.http.HttpServletRequest;

public class SubtractionAction extends AbstractCalculationAction {

  public SubtractionAction(HttpServletRequest httpServletRequest) {
    super(httpServletRequest);
  }

  @Override
  public float performAction() {
    return getFirstNumber() - getSecondNumber();
  }
}
