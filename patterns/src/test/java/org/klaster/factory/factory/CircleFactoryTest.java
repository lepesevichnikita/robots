/*
 * CircleFactoryTest
 *
 * practice
 *
 * 16:52
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.factory.factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.isA;

import org.klaster.factory.model.Circle;
import org.klaster.factory.model.Shape;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CircleFactoryTest {

  private ShapeFactory circleFactory;

  @BeforeMethod
  public void initialize() {
    circleFactory = new CircleFactory();
  }

  @Test
  public void createsShape() {
    assertThat(circleFactory.createShape(), isA(Shape.class));
  }

  @Test
  public void createsCircle() {
    assertThat(circleFactory.createShape(), isA(Circle.class));
  }

  @Test
  public void createdShapeNameIsCircle() {
    assertThat(circleFactory.createShape()
                            .getName(), equalTo("Circle"));
  }
}