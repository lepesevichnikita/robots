/*
 * org.klaster.service
 *
 * robots
 *
 * 2/4/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.action;

import javax.servlet.http.HttpServletRequest;

public class MultiplicationAction extends AbstractCalculationAction {

  public MultiplicationAction(HttpServletRequest httpServletRequest) {
    super(httpServletRequest);
  }

  @Override
  public float performAction() {
    return getFirstNumber() * getSecondNumber();
  }
}
