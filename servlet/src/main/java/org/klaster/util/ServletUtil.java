/*
 * org.klaster.util
 *
 * robots
 *
 * 2/4/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.klaster.action.CalculationAction;

public class ServletUtil {

  private ServletUtil() {
  }

  public static void tryToSendError(HttpServletResponse httpServletResponse,
                                    int errorCode,
                                    String message,
                                    Logger logger) {
    try {
      httpServletResponse.sendError(errorCode, message);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  public static void writeResponse(HttpServletResponse httpServletResponse, String responseMessage, Logger logger) {
    try (PrintWriter printWriter = httpServletResponse.getWriter()) {
      printWriter.print(responseMessage);
      printWriter.flush();
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  public static String tryToPerformAction(HttpServletRequest request,
                                          HttpServletResponse response,
                                          CalculationAction calculationAction,
                                          Logger logger) {
    String responseMessage = "";
    try {
      float sumOfTwoNumbers = calculationAction.performAction();
      responseMessage = Float.toString(sumOfTwoNumbers);
      ServletUtil.writeResponse(response, responseMessage, logger);
    } catch (IllegalArgumentException e) {
      logger.warning(e.getMessage());
      ServletUtil.tryToSendError(response, HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage(), logger);
    }
    return responseMessage;
  }
}
