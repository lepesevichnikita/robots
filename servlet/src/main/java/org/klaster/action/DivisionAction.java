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

public class DivisionAction extends AbstractCalculationAction {

  public DivisionAction(HttpServletRequest httpServletRequest) {
    super(httpServletRequest);
  }

  @Override
  public float performAction() throws IllegalArgumentException {
    return getFirstNumber() / getSecondNumber();
  }
}
