/*
 * CalculatorTest
 *
 * practice
 *
 * 17:11
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.strategy.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.klaster.strategy.procedure.Addition;
import org.klaster.strategy.procedure.Multiplication;
import org.klaster.strategy.procedure.Subtraction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTest {

  private Calculator calculator;

  @BeforeMethod
  public void initialize() {
    calculator = new Calculator();
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void throwsNullPointerExceptionAfterDefaultInitialization() {
    final float firstNumber = 1;
    final float secondNumber = 2;
    calculator.performProcedure(firstNumber, secondNumber);
  }

  @Test
  public void sumsTwoNumbers() {
    final float firstNumber = 1;
    final float secondNumber = 2;
    final float expectedNumber = 3;
    calculator.setCurrentProcedure(new Addition());
    assertThat(calculator.performProcedure(firstNumber, secondNumber), equalTo(expectedNumber));
  }

  @Test
  public void findsTheDifferenceOfTwoNumbers() {
    final float firstNumber = 1;
    final float secondNumber = 2;
    final float expectedNumber = -1;
    calculator.setCurrentProcedure(new Subtraction());
    assertThat(calculator.performProcedure(firstNumber, secondNumber), equalTo(expectedNumber));
  }

  @Test
  public void multiplicatesTwoNumbers() {
    final float firstNumber = 0.5f;
    final float secondNumber = 0.3f;
    final float expectedNumber = 0.15f;
    calculator.setCurrentProcedure(new Multiplication());
    assertThat(calculator.performProcedure(firstNumber, secondNumber), equalTo(expectedNumber));
  }
}