/*
 * Rectangle
 *
 * practice
 *
 * 16:01
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.files;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {

  private final Integer length;
  private Integer height;
  private List<String> body = new ArrayList<>();

  public Rectangle(Integer length) {
    this.length = length;
  }

  public String getColumn(Integer columnIndex) {
    final StringBuilder column = new StringBuilder();
    body.forEach((String row) -> column.append(row.charAt(columnIndex)));
    return column.toString();
  }

  public void addRow(String row) {
    if (row.length() == length) {

    }
  }


  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getLength() {
    return length;
  }
}
