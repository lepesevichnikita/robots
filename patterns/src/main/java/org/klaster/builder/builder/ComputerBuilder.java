package org.klaster.builder.builder;/*
 * ComputerBuilder
 *
 * practice
 *
 * 15:00
 *
 * Copyright(c) Nikita Lepesevich
 */

public interface ComputerBuilder {

  ComputerBuilder setRam(String ram);

  ComputerBuilder setHdd(String hdd);

  ComputerBuilder setGraphicsCard(String graphicsCard);

  ComputerBuilder setCpu(String cpu);

  void reset();
}
