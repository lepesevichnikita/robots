/*
 * HttpRequest
 *
 * practice
 *
 * 17:32
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.requests.model;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface HttpRequest {

  void send(HttpURLConnection httpURLConnection) throws IOException;

  void addHeader(String key, String value);

  void setBody(String body);

  void setHttpMethod(HttpMethod httpMethod);

}
