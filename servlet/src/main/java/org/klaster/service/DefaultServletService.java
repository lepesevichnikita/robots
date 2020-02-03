/*
 * org.klaster
 *
 * robots
 *
 * 2/3/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.service;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class DefaultServletService {

  private final Logger logger = Logger.getLogger(getClass().getName());

  public String sumTwoNumbers(HttpServletRequest httpServletRequest) throws NumberFormatException {
    String responseMessage = "";
    String firstValue = httpServletRequest.getParameter("firstNumber");
    String secondValue = httpServletRequest.getParameter("secondNumber");
    if (firstValue == null || secondValue == null) {
      throw new NumberFormatException("firstNumber and secondNumber are required");
    }
    float sumOfValues = Float.sum(Float.parseFloat(firstValue), Float.parseFloat(secondValue));
    responseMessage = Float.toString(sumOfValues);
    return responseMessage;
  }

}
