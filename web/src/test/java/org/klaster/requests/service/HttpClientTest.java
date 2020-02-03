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
import org.klaster.constant.DefaultResponse;
import org.klaster.endpoint.DefaultServlet;
import org.klaster.requests.model.HttpMethod;
import org.klaster.requests.model.HttpRequest;
import org.klaster.requests.model.HttpResponse;
import org.klaster.server.ServletServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HttpClientTest {

  private static final int PORT = 9090;
  private static final String PATH_SPEC = "/";
  private static final String CORRECT_URL = String.format("http://127.0.0.1:%s%s", PORT, PATH_SPEC);
  private static final String INCORRECT_URL = ("incorrect-url");
  private static ServletServer servletServer;
  private HttpClient httpClient;
  private DefaultHttpRequestBuilder defaultHttpRequestBuilder;

  @BeforeClass
  public static void runServer() throws Exception {
    servletServer = new ServletServer(PORT, DefaultServlet.class, PATH_SPEC);
    servletServer.run();
  }


  @BeforeMethod
  public void initialize() {
    httpClient = new HttpClient();
    defaultHttpRequestBuilder = new DefaultHttpRequestBuilder();
  }

  @AfterClass
  public void stopServlet() throws Exception {
    servletServer.stop();
  }

  @Test
  public void sendsGetRequestByCorrectUrlAndGetsHttpOk() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_OK));
  }

  @Test
  public void sendsGetRequestByCorrectUrlAndGetsDefaultResponse() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getBodyData(), equalTo(DefaultResponse.GET));
  }

  @Test
  public void failsGetRequestsByIncorrectUrl() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(INCORRECT_URL, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_BAD_REQUEST));
  }

  @Test
  public void sendsEmptyPostRequestByCorrectUrlAndGetsHttpOk() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.POST)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_OK));
  }

  @Test
  public void sendsEmptyPostRequestByCorrectUrlAndGetsDefaultResponse() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.POST)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getBodyData(), equalTo(DefaultResponse.POST));
  }

  @Test
  public void sendsEmptyPutRequestByCorrectUrlAndGetsHttpOk() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.PUT)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_OK));
  }

  @Test
  public void sendsEmptyPutRequestByCorrectUrlAndGetsDefaultResponse() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.PUT)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getBodyData(), equalTo(DefaultResponse.PUT));
  }

  @Test
  public void sendsEmptyDeleteRequestByCorrectUrlAndGetsHttpOk() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.DELETE)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_OK));
  }

  @Test
  public void sendsEmptyDeleteRequestByCorrectUrlAndGetsDefaultResponse() {
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.DELETE)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getBodyData(), equalTo(DefaultResponse.DELETE));
  }

  @Test
  public void sendsPostRequestWithCorrectParamsAndGetsExpectedResponse() {
    String body = "firstNumber=1&secondNumber=2";
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.POST)
                                                       .addHeader("Content-Length", Integer.toString(body.length()))
                                                       .setBody(body)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    String expectedResult = "3.0";
    assertThat(httpResponse.getBodyData(), equalTo(expectedResult));
  }

  @Test
  public void sendsPostRequestWithoutOneParameterAndGetsHttpPreconditionFailed() {
    String body = "firstNumber=1";
    HttpRequest httpRequest = defaultHttpRequestBuilder.setHttpMethod(HttpMethod.POST)
                                                       .addHeader("Content-Length", Integer.toString(body.length()))
                                                       .setBody(body)
                                                       .getDefaultHttpRequest();
    HttpResponse httpResponse = httpClient.sendRequest(CORRECT_URL, httpRequest);
    assertThat(httpResponse.getResponseCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
  }
}