/*
 * org.klaster.locator.service
 *
 * robots
 *
 * 1/30/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.locator.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {

  private Map<String, Service> serviceMap = new LinkedHashMap<>();

  public void addService(Service service) {
    String serviceName = service.getClass()
                                .getName();
    if (!serviceMap.containsKey(serviceName)) {
      serviceMap.put(serviceName, service);
    }
  }

  public Service getService(String serviceName) {
    return serviceMap.get(serviceName);
  }
}
