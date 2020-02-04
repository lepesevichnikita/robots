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
import org.klaster.util.ActionUtil;

abstract class AbstractCalculationAction implements CalculationAction {

  private final HttpServletRequest httpServletRequest;

  public AbstractCalculationAction(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  @Override
  public float getFirstNumber() throws IllegalArgumentException {
    String firstNumber = ActionUtil.getParameterFromRequest(httpServletRequest, "firstNumber");
    return Float.parseFloat(firstNumber);
  }

  @Override
  public float getSecondNumber() throws IllegalArgumentException {
    String secondNumber = ActionUtil.getParameterFromRequest(httpServletRequest, "secondNumber");
    return Float.parseFloat(secondNumber );
  }
}
