package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.controller.FileReader;
import cs3500.pa01.controller.WriteFile;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the file writer
 */
class WriteFileTest {
  Path newPath;
  Path badPath;
  File newFile;
  WriteFile fw;
  FileReader fr;
  ArrayList<Path> f1;
  ArrayList<Path> f2;

  /**
   * Initializes data for testing
   */
  @BeforeEach
  public void initData() {
    newPath = Path.of("src/test/resources/exampleDirectory/testFile.md");
    newFile = newPath.toFile();
    badPath = Path.of("");
    fw = new WriteFile();
    fr = new FileReader();
    f1 = new ArrayList<>(List.of(newPath));
    f2 = new ArrayList<>(List.of(badPath));

  }

  /**
   * tests the file writer
   */
  @Test
  public void testFileWriter() {
    fw.writeToFile(newFile, f1);
    assertEquals("# Testing\n", fr.readFiles(f1)[0]);
    assertThrows(
        RuntimeException.class,
        () -> fw.writeToFile(badPath.toFile(), f2));
  }

  @Test
  public void testRewrite() {
    this.initData();
    fw.rewriteFile(fr.readFiles(f1)[0], newPath);
    assertEquals("# Testing\n", fr.readFiles(f1)[0]);
    assertThrows(
        RuntimeException.class,
        () -> fw.rewriteFile("Bad", badPath));
  }
}