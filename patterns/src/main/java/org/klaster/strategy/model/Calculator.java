/*
 * Calculator
 *
 * practice
 *
 * 17:02
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.strategy.model;

import org.klaster.strategy.procedure.Procedure;

public class Calculator {

  private Procedure currentProcedure;
  private float firstNumber;
  private float secondNumber;

  public void setNumbers(float firstNumber, float secondNumber) {
    this.firstNumber = firstNumber;
    this.secondNumber = secondNumber;
  }

  public float performProcedure() {
    return currentProcedure.perform(firstNumber, secondNumber);
  }

  public void setCurrentProcedure(Procedure currentProcedure) {
    this.currentProcedure = currentProcedure;
  }
}
