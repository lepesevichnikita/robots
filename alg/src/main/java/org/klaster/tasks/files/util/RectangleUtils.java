/*
 * RectangleCreator
 *
 * practice
 *
 * 9:40
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.util;

import org.klaster.tasks.files.model.Rectangle;
import org.klaster.tasks.files.model.WordsContainer;

public class RectangleUtils {

  private RectangleUtils() {
  }

  public static boolean isRectangleCorrect(Rectangle rectangle, WordsContainer possibleColumns) {
    boolean result = false;
    if (rectangle.getHeight() <= possibleColumns.getWordsLength()) {
      result = rectangle.getColumns()
                        .stream()
                        .allMatch((String column) -> isColumnCorrect(column, possibleColumns));
    }
    return result;
  }

  private static boolean isColumnCorrect(String column, WordsContainer possibleColumns) {
    boolean result = false;
    if (column.length() <= possibleColumns.getWordsLength()) {
      result = possibleColumns.containsPrefix(column);
    }
    return result;
  }
}
