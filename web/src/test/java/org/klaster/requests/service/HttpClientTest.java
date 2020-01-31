/*
 * org.klaster.requests.serivce
 *
 * robots
 *
 * 1/31/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.requests.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.HttpURLConnection;
import org.klaster.requests.model.HttpRequest;
import org.klaster.requests.model.HttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HttpClientTest {

  private HttpClient httpClient;
  private DefaultHttpRequestBuilder defaultHttpRequestBuilder;

  @BeforeMethod
  public void initialize() {
    httpClient = new HttpClient();
    defaultHttpRequestBuilder = new DefaultHttpRequestBuilder();
  }

  @Test
  public void sendsGetRequestByCorrectUrlSuccessfully() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.getDefaultHttpRequest();
    final String url = "http://google.com";
    HttpResponse httpResponse = httpClient.sendRequest(url, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_OK));
  }

  @Test
  public void failsGetRequestsByIncorrectUrl() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.getDefaultHttpRequest();
    final String url = "http://127.0.0.9";
    HttpResponse httpResponse = httpClient.sendRequest(url, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_BAD_REQUEST));
  }
}