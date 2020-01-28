/*
 * Addition
 *
 * practice
 *
 * 17:07
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.strategy.procedure;

public class Addition implements Procedure {

  @Override
  public float perform(float firstNumber, float secondNumber) {
    return firstNumber + secondNumber;
  }
}
