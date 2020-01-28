/*
 * Multiplication
 *
 * practice
 *
 * 17:10
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.strategy.procedure;

public class Multiplication implements Procedure {

  @Override
  public float perform(float firstNumber, float secondNumber) {
    return firstNumber * secondNumber;
  }
}
