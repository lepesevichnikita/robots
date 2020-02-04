/*
 * org.klaster
 *
 * robots
 *
 * 2/3/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.action;

import javax.servlet.http.HttpServletRequest;

public class SummationAction extends AbstractCalculationAction {

  public SummationAction(HttpServletRequest httpServletRequest) {
    super(httpServletRequest);
  }

  @Override
  public float performAction() throws IllegalArgumentException {
    return getFirstNumber() + getSecondNumber();
  }
}
