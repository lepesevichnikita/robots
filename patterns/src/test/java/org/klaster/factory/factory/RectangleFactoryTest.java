/*
 * RectangleFactoryTest
 *
 * practice
 *
 * 16:55
 *
 * Copyright(c) Nikita Lepesevich
 *
 */

package org.klaster.factory.factory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.isA;

import org.klaster.factory.model.Rectangle;
import org.klaster.factory.model.Shape;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RectangleFactoryTest {

  private ShapeFactory circleFactory;

  @BeforeMethod
  public void initialize() {
    circleFactory = new RectangleFactory();
  }

  @Test
  public void createsShape() {
    assertThat(circleFactory.createShape(), isA(Shape.class));
  }

  @Test
  public void createsRectangle() {
    assertThat(circleFactory.createShape(), isA(Rectangle.class));
  }

  @Test
  public void createdShapeNameIsRectangle() {
    assertThat(circleFactory.createShape()
                            .getName(), equalTo("Rectangle"));
  }
}