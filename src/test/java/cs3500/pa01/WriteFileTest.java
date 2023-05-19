package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    newPath = Path.of("src/test/Resources/ExampleDirectory/testFile.md");
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
    assertEquals("# Testing\n", fr.readFiles(f1));
    assertThrows(
        RuntimeException.class,
        () -> fw.writeToFile(badPath.toFile(), f2));
  }
}