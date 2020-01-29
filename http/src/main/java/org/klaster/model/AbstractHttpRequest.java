/*
 * AbstractHttpRequest
 *
 * practice
 *
 * 17:46
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.model;

import java.net.HttpURLConnection;
import java.util.Map;

public class AbstractHttpRequest implements HTTPRequest {

  private String body;
  private Map<String, String> headers;
  private Map<String, String> params;

  @Override
  public HTTPResponse send(HttpURLConnection httpURLConnection) {
    return null;
  }

  @Override
  public void addHeader(String key, String value) {
    headers.put(key, value);
  }

  @Override
  public void addParameter(String key, String value) {
    params.put(key, value);
  }

  @Override
  public void setBody(String body) {
    this.body = body;
  }

  private class DefaultHttpResponse implements HTTPResponse {

    private final String body;
    private final int responseCode;

    private DefaultHttpResponse(String body, int responseCode) {
      this.body = body;
      this.responseCode = responseCode;
    }

    @Override
    public String getBody() {
      return this.body;
    }

    @Override
    public int getResponseCode() {
      return this.responseCode;
    }
  }
}
