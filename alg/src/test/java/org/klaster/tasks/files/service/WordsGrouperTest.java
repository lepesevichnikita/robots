/*
 * WordsGrouperTest
 *
 * practice
 *
 * 16:58
 *
 * Copyright(c) Nikita Lepesevich
 */

package org.klaster.tasks.files.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.klaster.tasks.files.model.WordsContainer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WordsGrouperTest {

  private WordsGrouper wordsGrouper;

  @BeforeClass
  public void initialize() {
    wordsGrouper = new WordsGrouper();
  }

  @Test
  @Parameters({"dictionary", "expectedWordsContainerNumber"})
  public void createsCorrectNumberOfContainers(String dictionary,
                                               int expectedWordsContainerNumber) {
    String[] words = Arrays.stream(dictionary.split(" "))
                           .map(word -> word.equals("null")
                                        ? null
                                        : word)
                           .collect(Collectors.toList())
                           .toArray(new String[]{});
    wordsGrouper.setDictionary(words);
    assertThat(wordsGrouper.groupWordsByLength()
                           .size(), equalTo(expectedWordsContainerNumber));
  }

  @Test
  @Parameters({"dictionary", "expectedWordsContainersSizes"})
  public void correctlyAddsWordsToContainers(String dictionary,
                                             String expectedWordsContainersSizes) {
    String[] words = Arrays.stream(dictionary.split(" "))
                           .map(word -> word.equals("null")
                                        ? null
                                        : word)
                           .collect(Collectors.toList())
                           .toArray(new String[]{});
    wordsGrouper.setDictionary(words);
    List<Integer> actualContainersSizes = wordsGrouper.groupWordsByLength()
                                                      .stream()
                                                      .map(WordsContainer::getSize)
                                                      .collect(Collectors.toList());
    Integer[] expectedContainersSizes = Arrays.stream(expectedWordsContainersSizes.split(" "))
                                              .mapToInt(Integer::parseInt)
                                              .boxed()
                                              .collect(Collectors.toList())
                                              .toArray(new Integer[]{});
    assertThat(actualContainersSizes, contains(expectedContainersSizes));
  }
}