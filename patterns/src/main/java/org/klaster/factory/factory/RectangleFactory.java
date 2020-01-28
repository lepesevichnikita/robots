package org.klaster.factory.factory;/*
 * RectangleFactory
 *
 * practice
 *
 * 16:50
 *
 * Copyright(c) Nikita Lepesevich
 */

import org.klaster.factory.model.Rectangle;
import org.klaster.factory.model.Shape;

public class RectangleFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new Rectangle();
  }
}
