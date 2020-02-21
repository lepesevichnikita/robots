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

import java.security.InvalidParameterException;
import javax.servlet.http.HttpServletRequest;

public class ActionUtil {

  private ActionUtil() {

  }

  public static String getParameterFromRequest(HttpServletRequest httpServletRequest, String parameterName) {
    String parameterValue = httpServletRequest.getParameter(parameterName);
    if (parameterValue == null) {
      throw new InvalidParameterException(String.format("Parameter %s is not found", parameterName));
    }
    return parameterValue;
  }

}
