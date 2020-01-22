/*
 * DictionaryReader
 *
 * practice
 *
 * 10:51
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.klaster.tasks.files.model.WordsContainer;

public class DictionaryReader {

  public static final String CHARSET_ENCODING = StandardCharsets.UTF_8.name();
  private static final Logger logger = Logger.getLogger(DictionaryReader.class.getName());
  private final WordsGrouper wordsGrouper = new WordsGrouper();
  private final String fileName;

  public DictionaryReader(String fileName) {
    this.fileName = fileName;
  }

  private static String getFilePathDecodedFromURL(URL url) throws UnsupportedEncodingException {
    return URLDecoder.decode(url.getFile(), CHARSET_ENCODING);
  }

  public List<String> readDictionary() {
    List<String> dictionary = new ArrayList<>();
    try {
      File file = getFileFromResources();
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        while (bufferedReader.ready()) {
          List<String> wordsFromLine = Arrays.stream(bufferedReader.readLine()
                                                                   .split(" "))
                                             .filter(word -> !word.isEmpty())
                                             .collect(Collectors.toList());
          dictionary.addAll(wordsFromLine);
        }
      } catch (IOException e) {
        logger.warning(e.getMessage());
      }
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      logger.warning(e.getMessage());
    }

    return dictionary;
  }

  public List<WordsContainer> readGroupedDictionary() {
    List<String> dictionary = readDictionary();
    wordsGrouper.setDictionary(dictionary.toArray(new String[0]));
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
}