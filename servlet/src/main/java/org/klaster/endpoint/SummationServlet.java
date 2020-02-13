/*
 * SummationServlet
 *
 * practice
 *
 * 16:15

 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.endpoint;

import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.klaster.action.CalculationAction;
import org.klaster.action.SummationAction;
import org.klaster.constant.DefaultResponse;
import org.klaster.util.ServletUtil;

public class SummationServlet extends HttpServlet {

  private final Logger logger = Logger.getLogger(getClass().getName());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String responseMessage = DefaultResponse.POST;
    if (!request.getParameterMap()
                .isEmpty()) {
      CalculationAction calculationAction = new SummationAction(request);
      responseMessage = ServletUtil.tryToPerformAction(request, response, calculationAction, logger);
    }
    ServletUtil.writeResponse(response, responseMessage, logger);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    ServletUtil.writeResponse(response, DefaultResponse.GET, logger);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {
    ServletUtil.writeResponse(response, DefaultResponse.PUT, logger);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    ServletUtil.writeResponse(response, DefaultResponse.DELETE, logger);
  }

}
