package org.klaster.requests.service;

import java.util.LinkedHashMap;
import java.util.Map;
import org.klaster.requests.model.DefaultHttpRequest;
import org.klaster.requests.model.HttpMethod;

public class DefaultHttpRequestBuilder {

  private String body;
  private Map<String, String> headers;
  private HttpMethod httpMethod;

  public DefaultHttpRequestBuilder() {
    reset();
  }

  public void reset() {
    body = "";
    headers = new LinkedHashMap<>();
    headers.put("User-Agent", "curl/7.65.1");
    httpMethod = HttpMethod.GET;
  }

  public DefaultHttpRequestBuilder setBody(String body) {
    this.body = body;
    return this;
  }

  public DefaultHttpRequestBuilder addHeader(String key, String value) {
    this.headers.put(key, value);
    return this;
  }

  public DefaultHttpRequestBuilder setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public DefaultHttpRequest build() {
    return new DefaultHttpRequest(body, headers, httpMethod);
  }
}