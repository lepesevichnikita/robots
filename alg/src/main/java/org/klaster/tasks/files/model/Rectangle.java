/*
 * Rectangle
 *
 * practice
 *
 * 16:01
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rectangle {

  private final Integer length;
  private List<String> rows = new ArrayList<>();

  public Rectangle(Rectangle source) {
    this.rows = new ArrayList<>(source.rows);
    this.length = source.length;
  }

  public Rectangle(Integer length) {
    this.length = length;
  }

  public String getColumn(Integer columnIndex) {
    final StringBuilder column = new StringBuilder();
    rows.stream()
        .map(row -> row.charAt(columnIndex))
        .forEach(column::append);
    return column.toString();
  }

  public void addRow(String row) {
    if (row.length() == length) {
      rows.add(row);
    }
  }

  public Integer getHeight() {
    return rows.size();
  }

  public Integer getLength() {
    return length;
  }

  public Integer getArea() {
    return length * rows.size();
  }

  public List<String> getRows() {
    return rows;
  }

  public void setRows(List<String> rows) {
    this.rows = rows;
  }

  public List<String> getColumns() {
    return IntStream.range(0, length)
                    .mapToObj(this::getColumn)
                    .collect(Collectors.toList());
  }

  public boolean isEmpty() {
    return rows.isEmpty();
  }
}
