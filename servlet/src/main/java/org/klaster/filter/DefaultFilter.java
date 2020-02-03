/*
 * DefaultFilter
 *
 * practice
 *
 * 13:59
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
    httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, DELETE, PUT, POST");
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    if (httpServletRequest.getMethod()
                          .equals("OPTIONS")) {
      resp.setStatus(HttpServletResponse.SC_ACCEPTED);
      return;
    }
    chain.doFilter(httpServletRequest, servletResponse);
  }

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
  }
}
