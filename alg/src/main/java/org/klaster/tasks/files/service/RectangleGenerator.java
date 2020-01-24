/*
 * RectangleGenerator
 *
 * practice
 *
 * 10:18
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.klaster.tasks.files.model.Rectangle;
import org.klaster.tasks.files.model.WordsContainer;
import org.klaster.tasks.files.util.RectangleUtils;

public class RectangleGenerator {

  private List<WordsContainer> wordsContainers;

  public Rectangle generateFirstMaximumPossibleRectangle() {
    final Integer maximumWordsLength = getMaximumWordsLength();
    final Integer maximumRectangleArea = maximumWordsLength * maximumWordsLength;
    List<Rectangle> allPossibleRectangles = generatePossibleRectanglesForEachArea(maximumRectangleArea);
    return getFirstPossibleRectangle(allPossibleRectangles);
  }

  public void setWordsContainers(List<WordsContainer> wordsContainers) {
    this.wordsContainers = wordsContainers;
  }

  public Rectangle generateFirstPossibleRectangleByPassedSize(Integer width, Integer height) {
    Rectangle result = new Rectangle(width);
    if (isSizeCorrect(width, height) && hasWordsForRectangleOfSize(width, height)) {
      completeRectangle(result, height);
    }
    return result;
  }

  private Rectangle getFirstPossibleRectangle(List<Rectangle> possibleRectanglesOfAllAreas) {
    return possibleRectanglesOfAllAreas.stream()
                                       .max(Comparator.comparingInt(Rectangle::getArea))
                                       .orElse(null);
  }

  private List<Rectangle> generatePossibleRectanglesForEachArea(Integer maximumRectangleArea) {
    List<Rectangle> possibleRectanglesForEachArea = new LinkedList<>();
    IntStream.rangeClosed(1, maximumRectangleArea)
             .boxed()
             .sorted(Collections.reverseOrder())
             .map(this::generatePossibleRectanglesOfThisArea)
             .filter(possibleRectanglesOfThisArea -> !possibleRectanglesOfThisArea.isEmpty())
             .forEach(possibleRectanglesForEachArea::addAll);
    return possibleRectanglesForEachArea;
  }

  private void completeRectangle(Rectangle rectangle, Integer expectedHeight) {
    WordsContainer wordsForRows = wordsContainers.get(rectangle.getLength() - 1);
    WordsContainer wordsForColumns = wordsContainers.get(expectedHeight - 1);
    IntStream.range(0, expectedHeight)
             .forEach(currentRowNumber -> findAllCorrectSubVariantsForCurrentRectangle(rectangle, wordsForRows,
                 wordsForColumns)
                 .stream()
                 .findFirst()
                 .ifPresent(rectangle1 -> rectangle.setRows(rectangle1.getRows())));
  }

  private List<Rectangle> findAllCorrectSubVariantsForCurrentRectangle(Rectangle rectangle,
                                                                       WordsContainer wordsForRows,
                                                                       WordsContainer wordsForColumns) {
    return wordsForRows.getWords()
                       .stream()
                       .map(word -> {
                         Rectangle testRectangle = new Rectangle(rectangle);
                         testRectangle.addRow(word);
                         return testRectangle;
                       })
                       .filter(testRectangle -> RectangleUtils.isRectangleCorrect(testRectangle, wordsForColumns))
                       .collect(Collectors.toList());
  }

  private Integer getMaximumWordsLength() {
    return wordsContainers.isEmpty()
           ? 0
           : wordsContainers.get(wordsContainers.size() - 1)
                            .getWordsLength();
  }

  private List<Rectangle> generatePossibleRectanglesOfThisArea(Integer currentRectangleArea) {
    final Integer maximumWordsLength = getMaximumWordsLength();
    return IntStream
        .rangeClosed(1, maximumWordsLength)
        .filter(currentRectangleHeight -> currentRectangleArea % currentRectangleHeight == 0)
        .mapToObj(
            currentRectangleHeight -> generateFirstPossibleRectangleByPassedSize(currentRectangleArea / currentRectangleHeight,
                currentRectangleHeight))
        .filter(rectangle -> !rectangle.isEmpty())
        .collect(Collectors.toList());
  }

  private boolean hasWordsForRectangleOfSize(Integer width, Integer height) {
    return !(wordsContainers.get(width - 1)
                            .isEmpty() || wordsContainers.get(height - 1)
                                                         .isEmpty());
  }

  private boolean isSizeCorrect(Integer width, Integer height) {
    return width <= wordsContainers.size() && height <= wordsContainers.size();
  }

}
