/*
 * DictionaryReader
 *
 * practice
 *
 * 10:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryReader {

  private final WordsGrouper wordsGrouper = new WordsGrouper();
  private String fileName;

  public List<String> readDictionary() throws IOException {
    final List<String> dictionary = new ArrayList<>();
    final File file = getFileFromResources();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      while (bufferedReader.ready()) {
        List<String> wordsFromLine = Arrays.stream(bufferedReader.readLine().split(" "))
                                           .filter(word -> !word.isEmpty()).collect(Collectors.toList());
        dictionary.addAll(wordsFromLine);
      }
    }
    return dictionary;
  }

  public List<WordsContainer> readGroupedDictionary() throws IOException {
    wordsGrouper.setDictionary(readDictionary().toArray(new String[]{}));
    return wordsGrouper.groupWordsByLength();
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  private File getFileFromResources() throws FileNotFoundException {
    File file = null;
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new FileNotFoundException();
    } else {
      file = new File(resource.getFile());
    }
    return file;
  }
}
