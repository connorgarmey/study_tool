package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.Created;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the Created comparator and its methods
 */
class CreatedTest {
  Comparator<Path> comp;
  String path1;
  String path2;
  File f1;
  File f2;
  Path p1;
  Path p2;
  BasicFileAttributeView a1;
  BasicFileAttributeView a2;

  /**
   * initializes the data for testing
   */

  @BeforeEach
  public void initData() {
    comp = new Created();
    path1 = "src/test/resources/exampleDirectory/Test1";
    path2 = "src/test/resources/exampleDirectory/Test2";
    f1 = null;
    f2 = null;

    try {
      f1 = File.createTempFile("connor1",
          ".md", new File(Path.of(path1).toUri()));
      f2 = File.createTempFile("connor2",
          ".md", new File(Path.of(path2).toUri()));
      a1 = Files.getFileAttributeView(
          f1.toPath(), BasicFileAttributeView.class);
      a2 = Files.getFileAttributeView(
          f2.toPath(), BasicFileAttributeView.class);

      long number = 34239;
      FileTime time = FileTime.fromMillis(number);
      a1.setTimes(time, time, time);
      p1 = Path.of(f1.toURI());
      p2 = Path.of(f2.toURI());

    } catch (IOException e) {
      System.err.println(e);
    }
  }

  /**
   * Tests the compare method by creating new temporary files
   * and assuring they are then ordered correctly
   */
  @Test
  public void testCreated() {
    assertTrue(comp.compare(p1, p2) < 0);
    assertTrue(comp.compare(p2, p1) > 0);
  }

  /*
   * Tests that an exception is thrown for files that don't exist

  @Test
  public void testException() {
    assertThrows(
        NullPointerException.class,
        () -> comp.compare(Path.of(fake), p1));
    assertThrows(
        NullPointerException.class,
        () -> comp.compare(p1, Path.of(fake)));
  }
  */

}


