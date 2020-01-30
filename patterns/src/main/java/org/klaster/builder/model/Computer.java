/*
 * Computer
 *
 * practice
 *
 * 14:50
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.builder.model;

import org.klaster.builder.util.BuilderUtil;

public class Computer {

  private String ram;
  private String hdd;
  private String graphicsCard;
  private String cpu;

  public Computer(String ram, String hdd, String graphicsCard, String cpu) {
    this.ram = ram;
    this.hdd = hdd;
    this.graphicsCard = graphicsCard;
    this.cpu = cpu;
  }

  public String getRam() {
    return ram;
  }

  public String getHdd() {
    return hdd;
  }

  public String getGraphicsCard() {
    return graphicsCard;
  }

  public String getCpu() {
    return cpu;
  }

  public String toString() {
    return BuilderUtil.serializeComputer(this);
  }
}
