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
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;
import org.klaster.model.HTTPMethod;

public class HTTPClient {

  private static final Logger logger = Logger.getLogger(HTTPClient.class.getName());
  private HttpURLConnection urlConnection;
  private URL url;

  public void setUrl(String url) {
    try {
      this.url = new URL(url);
    } catch (MalformedURLException e) {
      logger.warning(e.getMessage());
    }
  }

  public void openConnection() throws IOException {
    try {
      this.urlConnection = (HttpURLConnection) url.openConnection();
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }


  public void sendRequest(HTTPMethod httpMethod, Map<String, String> params, Map<String, String> headers) {
  }
}
