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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cache {

  private static Cache cache;
  private final List<Service> services;

  private Cache() {
    services = Collections.synchronizedList(new LinkedList<>());
  }

  public static Cache getInstance() {
    if (cache == null) {
      synchronized (Cache.class) {
        cache = new Cache();
      }
    }
    return cache;
  }

  public void addService(Service service) {
    synchronized (services) {
      if (service != null && !containsService(service)) {
          services.add(service);
        }
    }
  }

  public Service getService(String serviceClassName) {
    return services.stream()
                   .filter(service -> service.getClass()
                                             .getName()
                                             .equals(serviceClassName))
                   .findFirst()
                   .orElse(null);
  }

  private boolean containsService(Service requiredService) {
    return services.stream()
                   .anyMatch(service -> service.getClass()
                                               .equals(requiredService.getClass()));
  }
}
