/*
 * RectangleGenerator
 *
 * practice
 *
 * 10:18
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files;

import java.util.List;
import java.util.Optional;
import org.klaster.tasks.files.utils.RectangleUtils;

public class RectangleGenerator {

  private List<WordsContainer> wordsContainers;

  public Rectangle generateMaximumPossibleRectangle() {
    Rectangle result = null;
    Boolean rectangleIsGenerated = false;
    final Integer maximumWordsLength = wordsContainers.get(wordsContainers.size() - 1)
                                                      .getWordsLength();
    final Integer maximumRectangleArea = maximumWordsLength * maximumWordsLength;
    for (Integer currentRectangleArea = maximumRectangleArea; currentRectangleArea > 0;
        currentRectangleArea--) {
      for (Integer currentRectangleHeight = 1; currentRectangleHeight <= maximumWordsLength;
          currentRectangleHeight++) {
        if (currentRectangleArea % currentRectangleHeight == 0) {
          result = generateRectangle(currentRectangleArea / currentRectangleHeight,
              currentRectangleHeight);
          if (RectangleUtils.isRectangleComplete(result, currentRectangleHeight,
              wordsContainers.get(currentRectangleHeight - 1)).booleanValue()) {
            rectangleIsGenerated = true;
            break;
          }
        }
      }
      if (Boolean.TRUE.equals(rectangleIsGenerated)) {
        break;
      }
    }
    return result;
  }

  public void setWordsContainers(List<WordsContainer> wordsContainers) {
    this.wordsContainers = wordsContainers;
  }

  public Rectangle generateRectangle(Integer length, Integer height) {
    Rectangle result = new Rectangle(length);
    if (length <= wordsContainers.size() && height <= wordsContainers.size() &&
        !(wordsContainers.get(length - 1).isEmpty() || wordsContainers.get(height - 1).isEmpty())
    ) {
      completeRectangle(result, height);
    }
    return result;
  }

  private void completeRectangle(Rectangle rectangle, Integer expectedHeight) {
    final WordsContainer wordsForRows = wordsContainers.get(rectangle.getLength() - 1);
    final WordsContainer wordsForColumns = wordsContainers.get(expectedHeight - 1);
    for (Integer currentRowNumber = 0; currentRowNumber < expectedHeight; currentRowNumber++) {
      Optional<String> row = wordsForRows.getWords().stream().filter((String word) -> {
        Rectangle testRectangle = new Rectangle(rectangle);
        testRectangle.addRow(word);
        return RectangleUtils.isRectangleCorrect(testRectangle, wordsForColumns);
      }).findFirst();
      row.ifPresent(rectangle::addRow);
    }
  }
}
