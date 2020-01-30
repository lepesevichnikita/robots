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

  public float performProcedure(float firstNumber, float secondNumber) {
    return currentProcedure.perform(firstNumber, secondNumber);
  }

  public void setCurrentProcedure(Procedure currentProcedure) {
    this.currentProcedure = currentProcedure;
  }
}
