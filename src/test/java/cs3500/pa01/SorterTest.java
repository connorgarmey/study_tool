package cs3500.pa01;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.model.Created;
import cs3500.pa01.model.Sorter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the sorter class
 */
class SorterTest {
  Sorter sorter;
  ArrayList<Path> list;
  Path p1;
  Path p2;
  Path p3;
  Comparator<Path> comp;
  File f1;
  File f2;
  Path cp1;
  Path cp2;
  BasicFileAttributeView a1;
  BasicFileAttributeView a2;


  /**
   * Initializes data for testing
   */
  @BeforeEach
  public void initData() {
    sorter = new Sorter();
    p1 = Path.of("src/test/resources/exampleDirectory/oodNotes/arrays.md");
    p2 = Path.of("src/test/resources/exampleDirectory/oodNotes/io.md");
    p3 = Path.of("src/test/resources/exampleDirectory/oodNotes/vectors.md");
    list = new ArrayList<>(asList(p1, p2, p3));
    comp = new Created();


  }


  /**
   * tests the sort by filename
   */
  @Test
  public void testFilename() {
    assertArrayEquals(sorter.getSortedFiles(list, "filename").toArray(),
        new ArrayList<>(asList(p1, p2, p3)).toArray());
  }

  /**
   * tests the modified sort
   */
  @Test
  public void testModified() {
    try {
      Files.setLastModifiedTime(p1, FileTime.fromMillis(1000));
      Files.setLastModifiedTime(p2, FileTime.fromMillis(1));
      Files.setLastModifiedTime(p3, FileTime.fromMillis(100));
    } catch (IOException e) {
      System.err.println(e);
    }
    assertArrayEquals(sorter.getSortedFiles(list, "modified").toArray(),
        new ArrayList<>(asList(p2, p3, p1)).toArray());
  }


  /**
   * tests for invalid input
   */
  @Test
  public void testInvalidInput() {
    assertThrows(
        IllegalArgumentException.class,
        () -> sorter.getSortedFiles(list, "blah"));
  }
}

