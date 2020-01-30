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

public class InitialContext {

  public Service lookup(String serviceName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class serviceClass = getServiceClass(serviceName);
    return (Service) serviceClass.newInstance();
  }

  private Class<?> getServiceClass(String serviceClassName) throws ClassNotFoundException {
    return Class.forName(serviceClassName);
  }
}
