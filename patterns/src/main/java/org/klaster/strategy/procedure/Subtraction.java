/*
 * Subtraction
 *
 * practice
 *
 * 17:09
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.strategy.procedure;

public class Subtraction implements Procedure {

  @Override
  public float perform(float firstNumber, float secondNumber) {
    return firstNumber - secondNumber;
  }
}
