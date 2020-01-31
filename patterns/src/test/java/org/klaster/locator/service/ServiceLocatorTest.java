/*
 * ServiceLocatorTest
 *
 * practice
 *
 * 9:27
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.locator.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.isA;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ServiceLocatorTest {

  private ServiceLocator serviceLocator;

  @BeforeMethod
  public void initialize() {
    serviceLocator = new ServiceLocator();
  }

  @Test
  public void findsCorrectServiceIfItExists() {
    Service firstService = serviceLocator.lookup("org.klaster.locator.service.FirstService");
    assertThat(firstService, isA(FirstService.class));
  }

  @Test
  public void findsServicesCorrectly() {
    Service firstService = serviceLocator.lookup("org.klaster.locator.service.FirstService");
    Service secondService = serviceLocator.lookup("org.klaster.locator.service.SecondService");
    assertThat(firstService, not(isA(secondService.getClass())));
  }

  @Test
  public void firstServiceActsAsExpected() {
    Service firstService = serviceLocator.lookup("org.klaster.locator.service.FirstService");
    String expectedResult = "First service completed";
    assertThat(firstService.execute(), equalTo(expectedResult));
  }


  @Test
  public void secondServiceActsAsExpected() {
    Service secondService = serviceLocator.lookup("org.klaster.locator.service.SecondService");
    String expectedResult = "Second service completed";
    assertThat(secondService.execute(), equalTo(expectedResult));
  }

  @Test
  public void returnsNullIfServiceWithPassedNameDoesntExist() {
    Service undefinedService = serviceLocator.lookup("UndefinedService");
    assertThat(undefinedService, nullValue());
  }

  @Test
  public void findsSameInstanceOfServiceEveryTime() {
    Service firstTimeFoundService = serviceLocator.lookup("org.klaster.locator.service.FirstService");
    Service secondTimeFoundService = serviceLocator.lookup("org.klaster.locator.service.FirstService");
    assertThat(firstTimeFoundService, equalTo(secondTimeFoundService));
  }
}