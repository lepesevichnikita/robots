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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import org.klaster.requests.model.HttpRequest;
import org.klaster.requests.model.HttpResponse;

public class HttpClient {

  private static final int CONNECT_TIMEOUT = 3000;
  private static final int READ_TIMEOUT = 3000;
  private final Logger logger = Logger.getLogger(getClass().getName());
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
    int statusCode = tryToGetStatusCode();
    InputStream inputStream = tryToGetInputStream();
    return new DefaultHttpResponse(inputStream, statusCode);
  }

  private InputStream tryToGetInputStream() {
    InputStream inputStream = null;
    if (httpURLConnection != null) {
      try {
        inputStream = httpURLConnection.getInputStream();
      } catch (IOException e) {
        logger.warning(e.getMessage());
      }
    }
    return inputStream;
  }

  private int tryToGetStatusCode() {
    int responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
    if (httpURLConnection != null) {
      try {
        responseCode = httpURLConnection.getResponseCode();
      } catch (IOException e) {
        logger.warning(e.getMessage());
      }
    }
    return responseCode;
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
      httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
      httpURLConnection.setReadTimeout(READ_TIMEOUT);
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
    public String getBodyData() {
      String bodyData = "";
      if (body != null) {
        bodyData = tryToReadDataFromBody();
      }
      return bodyData;
    }

    @Override
    public int getResponseCode() {
      return this.responseCode;
    }

    private String tryToReadDataFromBody() {
      StringBuilder bodyData = new StringBuilder();
      try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body))) {
        String line = bufferedReader.readLine();
        while (line != null) {
          bodyData.append(line);
          line = bufferedReader.readLine();
        }
      } catch (IOException e) {
        logger.warning(e.getMessage());
      }
      return bodyData.toString();
    }
  }
}
