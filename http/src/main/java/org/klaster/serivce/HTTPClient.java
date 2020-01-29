/*
 * HTTPClient
 *
 * practice
 *
 * 17:03
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.serivce;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;
import org.klaster.model.HTTPMethod;
import org.klaster.model.HTTPRequest;
import org.klaster.model.HTTPResponse;

public class HTTPClient {

  private static final Logger logger = Logger.getLogger(HTTPClient.class.getName());
  private HttpURLConnection urlConnection;
  private HTTPRequest httpRequest;
  private HTTPResponse httpResponse;
  private URL url;


  public void sendRequest(String url, HTTPMethod httpMethod, Map<String, String> params, Map<String, String> headers) {
    openConnectionByUrl(url);

  }

  private void setHTTPRequestMethod(HTTPMethod httpMethod) {
    try {
      urlConnection.setRequestMethod(httpMethod.name());
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
  }

  private void openConnectionByUrl(String url) {
    setUrl(url);
    openConnection();
  }

  private void setUrl(String url) {
    try {
      this.url = new URL(url);
    } catch (MalformedURLException e) {
      logger.warning(e.getMessage());
    }
  }

  private void openConnection() {
    try {
      this.urlConnection = (HttpURLConnection) this.url.openConnection();
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

}
