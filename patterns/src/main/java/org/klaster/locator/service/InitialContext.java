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

  private final Package pack = this.getClass()
                                   .getPackage();

  public Service lookup(String serviceName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class serviceClass = getServiceClass(serviceName);
    Service service = (Service) serviceClass.newInstance();
    return service;
  }

  private Class<?> getServiceClass(String serviceName) throws ClassNotFoundException {
    return Class.forName(pack.getName()
                             .concat(".")
                             .concat(serviceName));
  }
}
