/*
 * HttpClient
 *
 * practice
 *
 * 17:03
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.requests.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import org.klaster.requests.model.HttpRequest;
import org.klaster.requests.model.HttpResponse;

public class HttpClient {

  private static final int CONNECT_TIMEOUT = 3000;
  private static final int READ_TIMEOUT = 3000;
  private static final Logger logger = Logger.getLogger(HttpClient.class.getName());
  private HttpURLConnection httpURLConnection;


  public HttpResponse sendRequest(String url, HttpRequest httpRequest) {
    URL parsedUrl = parseUrl(url);
    if (parsedUrl != null) {
      openConnection(parsedUrl);
      tryToSendHttpRequest(httpRequest);
    }
    return getHttpResponse();
  }

  private HttpResponse getHttpResponse() {
    HttpResponse httpResponse = new DefaultHttpResponse(null, HttpURLConnection.HTTP_BAD_REQUEST);
    try {
      int statusCode = httpURLConnection.getResponseCode();
      InputStream inputStream = httpURLConnection.getInputStream();
      httpResponse = new DefaultHttpResponse(inputStream, statusCode);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
    return httpResponse;
  }

  private URL parseUrl(String url) {
    URL parsedUrl = null;
    try {
      parsedUrl = new URL(url);
    } catch (MalformedURLException e) {
      logger.warning(e.getMessage());
    }
    return parsedUrl;
  }

  private void openConnection(URL url) {
    try {
      this.httpURLConnection = (HttpURLConnection) url.openConnection();
      this.httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      this.httpURLConnection.setReadTimeout(READ_TIMEOUT);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  private void tryToSendHttpRequest(HttpRequest httpRequest) {
    try {
      httpRequest.send(httpURLConnection);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  private class DefaultHttpResponse implements HttpResponse {

    private final InputStream body;
    private final int responseCode;

    private DefaultHttpResponse(InputStream body, int responseCode) {
      this.body = body;
      this.responseCode = responseCode;
    }

    @Override
    public InputStream getBody() {
      return this.body;
    }

    @Override
    public int getResponseCode() {
      return this.responseCode;
    }
  }
}
