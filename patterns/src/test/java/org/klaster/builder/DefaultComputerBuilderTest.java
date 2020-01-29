/*
 * DefaultComputerBuilderTest
 *
 * practice
 *
 * 14:59
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.klaster.builder.builder.DefaultComputerBuilder;
import org.klaster.builder.model.Computer;
import org.klaster.builder.util.BuilderUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DefaultComputerBuilderTest {

  private DefaultComputerBuilder defaultComputerBuilder;

  @BeforeMethod
  public void initialize() {
    defaultComputerBuilder = new DefaultComputerBuilder();
  }

  @Test
  public void buildDefaultComputerWithoutParams() {
    final String expectedComputer = BuilderUtil.serializeComputer(DefaultComputerBuilder.DEFAULT_RAM,
                                                                  DefaultComputerBuilder.DEFAULT_HDD,
                                                                  DefaultComputerBuilder.DEFAULT_GRAPHICS_CARD,
                                                                  DefaultComputerBuilder.DEFAULT_CPU);
    assertThat(defaultComputerBuilder.getComputer()
                                     .toString(), equalTo(expectedComputer));
  }

  @Test
  public void buildComputerWithAllParams() {
    final String newRam = "New RAM";
    final String newHdd = "New HDD";
    final String newGraphicsCard = "New graphics card";
    final String newCpu = "New CPU";
    Computer computer = defaultComputerBuilder.setRam(newRam)
                                              .setHdd(newHdd)
                                              .setGraphicsCard(newGraphicsCard)
                                              .getComputer();
    final String expectedComputer = BuilderUtil.serializeComputer(newRam, newHdd, newGraphicsCard, newCpu);
    assertThat(computer.toString(), equalTo(expectedComputer));
  }

  @Test
  public void buildsComputerWithNulls() {
    final String newRam = null;
    final String newHdd = null;
    final String newGraphicsCard = null;
    final String newCpu = null;
    Computer computer = defaultComputerBuilder.setRam(newRam)
                                              .setHdd(newHdd)
                                              .setGraphicsCard(newGraphicsCard)
                                              .setCpu(newCpu)
                                              .getComputer();
    final String expectedComputer = BuilderUtil.serializeComputer(newRam, newHdd, newGraphicsCard, newCpu);
    assertThat(computer.toString(), equalTo(expectedComputer));
  }
}