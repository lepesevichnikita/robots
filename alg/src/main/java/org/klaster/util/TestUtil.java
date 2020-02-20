package org.klaster.util;

/*
 * practice
 *
 * 20.02.2020
 *
 */

import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.csv.CsvConfiguration;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;

/**
 * TestUtil
 *
 * @author Nikita Lepesevich
 */

public class TestUtil {

  public static final String SOURCE_POSTFIX = "_source.xls";
  public static final String EXPECTED_POSTFIX = "_target.xls";

  private TestUtil() {
  }

  public static Integer[] arrayOfIntegersFromString(String source, String separator) {
    return Arrays.stream(arrayOfStringFromString(source, separator))
                 .mapToInt(Integer::parseInt)
                 .boxed()
                 .toArray(Integer[]::new);
  }

  public static String[] arrayOfStringFromString(String source, String separator) {
    return Arrays.stream(source.split(separator))
                 .map(word -> word.equals("null")
                              ? null
                              : word)
                 .toArray(String[]::new);
  }

  public static Object[][] getValuesFromCsvFiles(List<File> files) {
    List<Object[]> data = new LinkedList<>();
    CsvConfiguration conf = new CsvConfiguration(1);
    files.forEach(file -> {
      DataContext csvContext = DataContextFactory.createCsvDataContext(file, conf);
      Schema schema = csvContext.getDefaultSchema();
      Table table = schema.getTables()
                          .get(0);
      DataSet dataSet = csvContext.query()
                                  .from(table)
                                  .selectAll()
                                  .execute();
      List<Row> rows = dataSet.toRows();
      rows.stream()
          .map(Row::getValues)
          .forEach(data::add);
    });
    return data.toArray(new Object[][]{});
  }

  public static Map<String, String> getPairsFromFolder(File folder) {
    List<String> filePathsFromFolder = getFilePathsFromFolder(folder);
    return makePairsFromList(filePathsFromFolder);
  }

  public static List<File> getFilesFromFolder(File folder, String extension) {
    return getFilePathsFromFolder(folder).stream()
                                         .filter(filePath -> filePath.endsWith(extension))
                                         .map(File::new)
                                         .collect(Collectors.toList());
  }

  public static List<String> getFilePathsFromFolder(File folder) {
    File[] entries = folder.listFiles();
    String folderPath = getFolderPath(folder);
    return Arrays.stream(Objects.requireNonNull(entries))
                 .filter(File::isFile)
                 .map(File::getName)
                 .map(folderPath::concat)
                 .collect(Collectors.toList());
  }

  private static Map<String, String> makePairsFromList(List<String> list) {
    Map<String, String> map = new HashMap<>();
    List<String> expectedFilePaths = list.stream()
                                         .filter(filePath -> filePath.endsWith(EXPECTED_POSTFIX))
                                         .collect(Collectors.toList());
    List<String> sourceFilesPaths = list.stream()
                                        .filter(filePath -> filePath.endsWith(SOURCE_POSTFIX))
                                        .collect(Collectors.toList());
    sourceFilesPaths.stream()
                    .map(sourceFilePath -> new SimpleEntry<>(sourceFilePath, findExpectedFilePathWithSameName(sourceFilePath, expectedFilePaths)))
                    .filter(entry -> entry.getValue() != null)
                    .forEach(entry -> map.put(entry.getKey(), entry.getValue()));
    return map;
  }

  private static String findExpectedFilePathWithSameName(String sourceFilePath, List<String> expectedFilePaths) {
    String sourceFilePathWithoutPostfix = sourceFilePath.replace(SOURCE_POSTFIX, "");
    return expectedFilePaths.stream()
                            .filter(expectedFilesPath -> expectedFilePaths.contains(sourceFilePathWithoutPostfix))
                            .findFirst()
                            .orElse(null);
  }

  private static String getFolderPath(File folder) {
    return folder.toString()
                 .replace("\\", "/")
                 .concat("/");
  }

}
