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

import java.util.logging.Logger;

public class ServiceLocator {

  private static final Logger logger = Logger.getLogger(ServiceLocator.class.getName());

  public Service lookup(String serviceName) {
    Cache cache = Cache.getInstance();
    Service service = cache.getService(serviceName);
    if (service == null) {
      service = tryToLookupServiceFromInitialContext(serviceName);
      cache.addService(service);
    }
    return service;
  }

  private Service tryToLookupServiceFromInitialContext(String serviceName) {
    InitialContext initialContext = new InitialContext();
    Service service = null;
    try {
      service = initialContext.lookup(serviceName);
    } catch (ClassNotFoundException e) {
      warningServiceNotFound(serviceName, e);
    } catch (IllegalAccessException | InstantiationException e) {
      warningNotPossibleToInstantiateService(serviceName, e);
    }
    return service;
  }

  private void warningServiceNotFound(String serviceName, ClassNotFoundException e) {
    String message = String.format("Service with name %s not found%n%s", serviceName, e.getMessage());
    logger.warning(message);
  }

  private void warningNotPossibleToInstantiateService(String serviceName, ReflectiveOperationException e) {
    String message = String.format("Not possible to instantiate service with name %s%nError: %s$n%s",
                                   serviceName,
                                   e.getClass()
                                    .getName(),
                                   e.getMessage());
    logger.warning(message);
  }

}
