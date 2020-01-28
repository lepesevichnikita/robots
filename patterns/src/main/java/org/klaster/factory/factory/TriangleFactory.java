/*
 * TriangleFactory
 *
 * practice
 *
 * 16:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.factory.factory;

import org.klaster.factory.model.Shape;
import org.klaster.factory.model.Triangle;

public class TriangleFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new Triangle();
  }
}
