/*
 * DictionaryReader
 *
 * practice
 *
 * 10:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.klaster.tasks.files.model.WordsContainer;
import org.klaster.tasks.files.service.WordsGrouper;

public class DictionaryReader {

  public static final String CHARSET_ENCODING = StandardCharsets.UTF_8.name();
  private static final Logger logger = Logger.getLogger(DictionaryReader.class.getName());
  private final WordsGrouper wordsGrouper = new WordsGrouper();
  private String fileName;

  private static String getFilePathDecodedFromURL(URL url) throws UnsupportedEncodingException {
    return URLDecoder.decode(url.getFile(), CHARSET_ENCODING);
  }

  public String[] readDictionary() {
    List<String> dictionary = new ArrayList<>();
    try {
      File file = getFileFromResources();
      readAllWordsFromFileIntoDictionary(dictionary, file);
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      logger.log(Level.WARNING, e.getMessage());
    }

    return dictionary.toArray(new String[0]);
  }

  public List<WordsContainer> readGroupedDictionary() {
    String[] dictionary = readDictionary();
    wordsGrouper.setDictionary(dictionary);
    return wordsGrouper.groupWordsByLength();
  }

  private File getFileFromResources() throws FileNotFoundException, UnsupportedEncodingException {
    URL url = getURLOfResourceByFileName();
    if (url == null) {
      throw new FileNotFoundException();
    }
    final String filePath = getFilePathDecodedFromURL(url);
    return new File(filePath);
  }

  private URL getURLOfResourceByFileName() {
    return getClass().getClassLoader()
                     .getResource(fileName);
  }

  private void readAllWordsFromFileIntoDictionary(List<String> dictionary, File file) {
    try {
      Files.readAllLines(file.toPath())
           .forEach(line -> Arrays.stream(line.split(" "))
                                  .filter(word -> !word.isEmpty())
                                  .forEach(dictionary::add));
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
