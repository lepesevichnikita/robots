/*
 * HttpResponse
 *
 * practice
 *
 * 17:43
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.requests.model;

import java.io.InputStream;

public interface HttpResponse {

  InputStream getBody();

  String getBodyData();

  int getResponseCode();
}
