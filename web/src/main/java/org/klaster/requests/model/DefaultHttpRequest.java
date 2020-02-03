/*
 * DefaultHttpRequest
 *
 * practice
 *
 * 17:46
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.requests.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Map;
import java.util.logging.Logger;

public class DefaultHttpRequest implements HttpRequest {

  private final Logger logger = Logger.getLogger(this.getClass()
                                                     .getName());

  private String body;
  private Map<String, String> headers;
  private HttpMethod httpMethod;

  public DefaultHttpRequest(String body, Map<String, String> headers, HttpMethod httpMethod) {
    this.body = body;
    this.headers = headers;
    this.httpMethod = httpMethod;
  }

  @Override
  public void send(HttpURLConnection httpURLConnection) throws ProtocolException {
    httpURLConnection.setRequestMethod(httpMethod.name());
    writeHeaders(httpURLConnection);
    writeBody(httpURLConnection);
  }

  @Override
  public void addHeader(String key, String value) {
    headers.put(key, value);
  }

  @Override
  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  @Override
  public void setBody(String body) {
    this.body = body;
  }

  private void writeHeaders(HttpURLConnection httpURLConnection) {
    headers.forEach(httpURLConnection::setRequestProperty);
  }

  private void writeBody(HttpURLConnection httpURLConnection) {
    if (!body.isEmpty()) {
      httpURLConnection.setDoOutput(true);
      try (OutputStream dataOutputStream = httpURLConnection.getOutputStream()) {
        dataOutputStream.write(body.getBytes());
      } catch (IOException e) {
        logger.warning(e.getMessage());
      } finally {
        flushOutputStream(httpURLConnection);
      }
    }
  }

  private void flushOutputStream(HttpURLConnection httpURLConnection) {
    if (httpURLConnection.getDoOutput()) {
      try (OutputStream dataOutputStream = httpURLConnection.getOutputStream()) {
        dataOutputStream.flush();
      } catch (IOException e) {
        logger.warning(e.getMessage());
      }
    }
  }

}
