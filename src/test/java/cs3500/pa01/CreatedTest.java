package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
  Path fake;
  Path fake2;
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
    fake = Path.of("src/test/Resources/ExampleDirectory/OODNotes/fake.md");
    fake2 = Path.of("src/test/Resources/ExampleDirectory/OODNotes/fake2.md");
    f1 = null;
    f2 = null;

    try {
      f1 = File.createTempFile("connor1",
          ".md", new File("src/test/Resources/ExampleDirectory/Test1"));
      f2 = File.createTempFile("stinky2",
          ".md", new File("src/test/Resources/ExampleDirectory/Test2"));
      a1 = Files.getFileAttributeView(
          f1.toPath(), BasicFileAttributeView.class);
      a2 = Files.getFileAttributeView(
          f2.toPath(), BasicFileAttributeView.class);

      long number = 34239;
      FileTime time = FileTime.fromMillis(number);
      a1.setTimes(time, time, time);

    } catch (IOException e) {
      System.err.println(e);
      fail();
    }
    p1 = Path.of(f1.toURI());
    p2 = Path.of(f2.toURI());
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

  /**
   * Tests that an exception is thrown for files that don't exist
   */
  @Test
  public void testException() {
    assertThrows(
        RuntimeException.class,
        () -> comp.compare(fake, p1));
    assertThrows(
        RuntimeException.class,
        () -> comp.compare(p1, fake));
  }
}

