/*
 * org.klaster.server
 *
 * robots
 *
 * 2/2/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.server;

import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ServletServer {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private final Server server;
  private final int port;
  private final Class<? extends HttpServlet> httpServletImplementation;
  private final String servletPathSpec;

  public ServletServer(int port, Class<? extends HttpServlet> httpServletImplementation, String servletPathSpec) {
    server = new Server();
    this.port = port;
    this.httpServletImplementation = httpServletImplementation;
    this.servletPathSpec = servletPathSpec;
  }

  public void run() {
    configureServer();
    try {
      server.start();
    } catch (Exception e) {
      logger.warning(e.getMessage());
    }
  }

  public void stop() {
    try {
      server.stop();
    } catch (Exception e) {
      logger.warning(e.getMessage());
    }
  }

  private void configureServer() {
    ServerConnector serverConnector = new ServerConnector(server);
    serverConnector.setPort(port);
    server.addConnector(serverConnector);
    ServletContextHandler servletContextHandler = new ServletContextHandler();
    servletContextHandler.addServlet(httpServletImplementation, servletPathSpec);
    HandlerCollection handlerCollection = new HandlerCollection();
    handlerCollection.setHandlers(new Handler[]{servletContextHandler, new DefaultHandler()});
    server.setHandler(handlerCollection);
  }

}
