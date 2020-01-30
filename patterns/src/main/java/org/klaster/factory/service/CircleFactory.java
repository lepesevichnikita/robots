/*
 * CircleFactory
 *
 * practice
 *
 * 16:49
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.factory.service;

import org.klaster.factory.model.Circle;
import org.klaster.factory.model.Shape;

public class CircleFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new Circle();
  }
}
