/*
 * org.klaster.service
 *
 * robots
 *
 * 2/4/20
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.action;

public interface CalculationAction {

  float performAction() throws IllegalArgumentException;

  float getFirstNumber() throws IllegalArgumentException;

  float getSecondNumber() throws IllegalArgumentException;
}
