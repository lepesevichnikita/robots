/*
 * DefaultComputerBuilder
 *
 * practice
 *
 * 14:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.builder.service;

import org.klaster.builder.model.Computer;

public class DefaultComputerBuilder implements ComputerBuilder {

  public static final String DEFAULT_RAM = "Default RAM";
  public static final String DEFAULT_HDD = "Default HDD";
  public static final String DEFAULT_GRAPHICS_CARD = "Default graphics card";
  public static final String DEFAULT_CPU = "Default CPU";

  private String ram;
  private String hdd;
  private String graphicsCard;
  private String cpu;

  public DefaultComputerBuilder() {
    reset();
  }

  @Override
  public DefaultComputerBuilder setRam(String ram) {
    this.ram = ram;
    return this;
  }

  @Override
  public DefaultComputerBuilder setHdd(String hdd) {
    this.hdd = hdd;
    return this;
  }

  @Override
  public DefaultComputerBuilder setGraphicsCard(String graphicsCard) {
    this.graphicsCard = graphicsCard;
    return this;
  }

  @Override
  public DefaultComputerBuilder setCpu(String cpu) {
    this.cpu = cpu;
    return this;
  }

  @Override
  public void reset() {
    cpu = DEFAULT_CPU;
    ram = DEFAULT_RAM;
    hdd = DEFAULT_HDD;
    graphicsCard = DEFAULT_GRAPHICS_CARD;
  }

  public Computer getComputer() {
    Computer computer = new Computer();
    computer.setCpu(cpu);
    computer.setRam(ram);
    computer.setHdd(hdd);
    computer.setGraphicsCard(graphicsCard);
    return computer;
  }

}
