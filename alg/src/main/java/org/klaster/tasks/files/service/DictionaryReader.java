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
import java.util.stream.Collectors;
import org.klaster.tasks.files.model.WordsContainer;

public class DictionaryReader {

  public static final String CHARSET_ENCODING = StandardCharsets.UTF_8.name();

  private final WordsGrouper wordsGrouper = new WordsGrouper();
  private String fileName;

  private static String getFilePathDecodedFromURL(URL url) throws UnsupportedEncodingException {
    return URLDecoder.decode(url.getFile(), CHARSET_ENCODING);
  }

  public List<String> readDictionary() throws IOException {
    List<String> dictionary = new ArrayList<>();
    File file = getFileFromResources();
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

  private File getFileFromResources() throws FileNotFoundException, UnsupportedEncodingException {
    File file = null;
    URL url = getURLOfResourceByFileName();
    if (url == null) {
      throw new FileNotFoundException();
    }
    final String filePath = getFilePathDecodedFromURL(url);
    file = new File(filePath);
    return file;
  }

  private URL getURLOfResourceByFileName() {
    return getClass().getClassLoader().getResource(fileName);
  }
}
