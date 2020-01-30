/*
 * HttpClient
 *
 * practice
 *
 * 17:03
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.requests.serivce;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import org.klaster.requests.model.HttpRequest;
import org.klaster.requests.model.HttpResponse;

public class HttpClient {

  private static final Logger logger = Logger.getLogger(HttpClient.class.getName());
  private HttpURLConnection httpURLConnection;


  public void sendRequest(String url, HttpRequest httpRequest) {
    parseUrlAndOpenConnection(url);
    try {
      httpRequest.send(httpURLConnection);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  public HttpResponse getHttpResponse() {
    HttpResponse httpResponse = null;
    try {
      int statusCode = httpURLConnection.getResponseCode();
      InputStream inputStream = httpURLConnection.getInputStream();
      httpResponse = new DefaultHttpResponse(inputStream, statusCode);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
    return httpResponse;
  }

  private void parseUrlAndOpenConnection(String url) {
    try {
      URL newUrl = new URL(url);
      openConnection(newUrl);
    } catch (MalformedURLException e) {
      logger.warning(e.getMessage());
    }
  }

  private void openConnection(URL url) {
    try {
      this.httpURLConnection = (HttpURLConnection) url.openConnection();
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
