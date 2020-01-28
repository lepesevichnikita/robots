/*
 * BuilderUtil
 *
 * practice
 *
 * 15:12
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.builder.util;

import org.klaster.builder.model.Computer;

public class BuilderUtil {

  private static String COMPUTER_SERIALIZING_TEMPLATE = "Computer [ram=%s, hdd=%s, graphicsCard=%s, cpu=%s]";

  private BuilderUtil() {
  }

  public static String serializeComputer(Computer computer) {
    return serializeComputer(computer.getRam(), computer.getHdd(), computer.getGraphicsCard(), computer.getCpu());
  }

  public static String serializeComputer(String ram, String hdd, String graphicsCard, String cpu) {
    return String.format(COMPUTER_SERIALIZING_TEMPLATE, ram, hdd, graphicsCard, cpu);
  }
}
