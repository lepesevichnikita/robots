/*
 * HTTPRequest
 *
 * practice
 *
 * 17:32
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.model;

import java.net.HttpURLConnection;

public interface HTTPRequest {

  HTTPResponse send(HttpURLConnection httpURLConnection);

  void addHeader(String key, String value);

  void addParameter(String key, String value);

  void setBody(String body);
}
