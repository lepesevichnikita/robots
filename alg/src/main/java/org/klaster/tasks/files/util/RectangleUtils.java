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

  public static Boolean isRectangleComplete(Rectangle rectangle,
                                            Integer expectedHeight,
                                            WordsContainer possibleColumns) {
    return rectangle.getHeight().equals(expectedHeight) &&
           expectedHeight.equals(possibleColumns.getWordsLength()) &&
           isRectangleCorrect(rectangle, possibleColumns);
  }

  public static Boolean isRectangleCorrect(Rectangle rectangle, WordsContainer possibleColumns) {
    Boolean result = false;
    if (rectangle.getHeight() <= possibleColumns.getWordsLength()) {
      result = rectangle.getColumns().stream()
                        .allMatch((String column) -> isColumnCorrect(column, possibleColumns));
    }
    return result;
  }

  private static Boolean isColumnCorrect(String column, WordsContainer possibleColumns) {
    Boolean result = false;
    if (column.length() <= possibleColumns.getWordsLength()) {
      result = possibleColumns.containsPrefix(column);
    }
    return result;
  }
}
