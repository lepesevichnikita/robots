/*
 * DefaultServlet
 *
 * practice
 *
 * 16:15

 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.endpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.klaster.constant.DefaultResponse;
import org.klaster.service.DefaultServletService;

public class DefaultServlet extends HttpServlet {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private final DefaultServletService defaultServletService = new DefaultServletService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String responseMessage = DefaultResponse.POST;
    if (!request.getParameterMap()
                .isEmpty()) {
      responseMessage = tryToCalculateSumOfRequestParams(request, response);
    }
    writeResponse(response, responseMessage);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    writeResponse(response, DefaultResponse.GET);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {
    writeResponse(response, DefaultResponse.PUT);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    writeResponse(response, DefaultResponse.DELETE);
  }

  private void writeResponse(HttpServletResponse httpServletResponse, String responseMessage) {
    try (PrintWriter printWriter = httpServletResponse.getWriter()) {
      printWriter.print(responseMessage);
      printWriter.flush();
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  private void tryToSendError(HttpServletResponse httpServletResponse, int errorCode, String message) {
    try {
      httpServletResponse.sendError(errorCode, message);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  private String tryToCalculateSumOfRequestParams(HttpServletRequest request, HttpServletResponse response) {
    String responseMessage = "";
    try {
      String sumOfTwoNumbers = defaultServletService.sumTwoNumbers(request);
      if (!sumOfTwoNumbers.isEmpty()) {
        responseMessage = sumOfTwoNumbers;
      }
      writeResponse(response, responseMessage);
    } catch (NumberFormatException e) {
      logger.warning(e.getMessage());
      tryToSendError(response, HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
    }
    return responseMessage;
  }
}
