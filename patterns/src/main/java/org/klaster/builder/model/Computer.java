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

  public String getRam() {
    return ram;
  }

  public void setRam(String ram) {
    this.ram = ram;
  }

  public String getHdd() {
    return hdd;
  }

  public void setHdd(String hdd) {
    this.hdd = hdd;
  }

  public String getGraphicsCard() {
    return graphicsCard;
  }

  public void setGraphicsCard(String graphicsCard) {
    this.graphicsCard = graphicsCard;
  }

  public String getCpu() {
    return cpu;
  }

  public void setCpu(String cpu) {
    this.cpu = cpu;
  }

  public String toString() {
    return BuilderUtil.serializeComputer(this);
  }
}
