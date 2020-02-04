/*
 * org.klaster.endpoint
 *
 * robots
 *
 * 2/4/20
 *
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

public class SubtractionServlet extends HttpServlet {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private CalculationAction calculationAction;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String responseMessage = DefaultResponse.POST;
    if (!request.getParameterMap()
                .isEmpty()) {
      calculationAction = new SummationAction(request);
      responseMessage = ServletUtil.tryToPerformAction(request, response, calculationAction, logger);
    }
    ServletUtil.writeResponse(response, responseMessage, logger);
  }
}
