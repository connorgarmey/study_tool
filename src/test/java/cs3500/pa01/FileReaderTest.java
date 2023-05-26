package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.controller.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the File Reader class and its two methods
 */
class FileReaderTest {
  Path simpleFile;
  Path fake;
  FileReader fr;
  ArrayList<Path> list;
  ArrayList<Path> badList;

  /**
   * initializes the testing data
   */
  @BeforeEach
  public void initData() {
    simpleFile = Path.of("src/test/resources/exampleDirectory/aa.md");
    fake = Path.of("src/test/resources/exampleDirectory/oodNotes/fake.md");
    fr = new FileReader();
    list = new ArrayList<>(List.of(simpleFile));
    badList = new ArrayList<>(List.of(simpleFile, fake));
  }

  /**
   * tests the reading of files
   */
  @Test
  public void testReadFiles() {
    assertEquals("""
        # Study Guide
        - by Connor Garmey
        - Welcome to the study guide
        """, fr.readFiles(list)[0]);
    assertThrows(
        RuntimeException.class,
        () -> fr.readFiles(badList));
  }

}