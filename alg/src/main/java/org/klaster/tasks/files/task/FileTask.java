/*
 * FileTask
 *
 * practice
 *
 * 10:39
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.task;

import java.util.List;
import org.klaster.tasks.files.input.DictionaryReader;
import org.klaster.tasks.files.model.Rectangle;
import org.klaster.tasks.files.model.WordsContainer;
import org.klaster.tasks.files.service.RectangleGenerator;

public class FileTask {

  private final DictionaryReader dictionaryReader = new DictionaryReader();
  private final RectangleGenerator rectangleGenerator = new RectangleGenerator();
  private String fileName;

  public Rectangle readDictionaryAndGenerateFirstMaximumPossibleRectangle() {
    dictionaryReader.setFileName(fileName);
    List<WordsContainer> groupedWords = dictionaryReader.readGroupedDictionary();
    rectangleGenerator.setWordsContainers(groupedWords);
    return rectangleGenerator.generateFirstMaximumPossibleRectangle();
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
