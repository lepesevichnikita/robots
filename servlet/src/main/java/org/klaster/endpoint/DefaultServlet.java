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

public class DefaultServlet extends HttpServlet {

  private final Logger logger = Logger.getLogger(getClass().getName());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    writeResponse(response, DefaultResponse.POST);
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
}
