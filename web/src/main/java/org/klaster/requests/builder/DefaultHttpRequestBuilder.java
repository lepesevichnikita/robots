package org.klaster.requests.builder;

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
    httpMethod = HttpMethod.GET;
  }

  public DefaultHttpRequestBuilder setBody(String body) {
    this.body = body;
    return this;
  }

  public DefaultHttpRequestBuilder setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public DefaultHttpRequestBuilder setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public DefaultHttpRequest createDefaultHttpRequest() {
    return new DefaultHttpRequest(body, headers, httpMethod);
  }
}