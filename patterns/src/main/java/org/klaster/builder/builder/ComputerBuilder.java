/*
 * ComputerBuilder
 *
 * practice
 *
 * 15:00
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.builder.builder;

public interface ComputerBuilder {

  ComputerBuilder setRam(String ram);

  ComputerBuilder setHdd(String hdd);

  ComputerBuilder setGraphicsCard(String graphicsCard);

  ComputerBuilder setCpu(String cpu);

  void reset();
}
