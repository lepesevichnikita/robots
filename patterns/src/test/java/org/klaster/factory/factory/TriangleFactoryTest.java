/*
 * TriangleFactoryTest
 *
 * practice
 *
 * 16:57
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.factory.factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.isA;

import org.klaster.factory.model.Shape;
import org.klaster.factory.model.Triangle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TriangleFactoryTest {

  private ShapeFactory circleFactory;

  @BeforeMethod
  public void initialize() {
    circleFactory = new TriangleFactory();
  }

  @Test
  public void createsShape() {
    assertThat(circleFactory.createShape(), isA(Shape.class));
  }

  @Test
  public void createsTriangle() {
    assertThat(circleFactory.createShape(), isA(Triangle.class));
  }

  @Test
  public void createdShapeNameIsTriangle() {
    assertThat(circleFactory.createShape()
                            .getName(), equalTo("Triangle"));
  }
}