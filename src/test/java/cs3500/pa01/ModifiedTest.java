package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa01.model.Modified;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the Modified comparator and its methods
 */
class ModifiedTest {
  Path arrays;
  Path vectors;
  Path fake;
  Comparator<Path> comp;

  /**
   * Initializes data for testing
   */
  @BeforeEach
  public void initData() {
    arrays = Path.of("src/test/Resources/ExampleDirectory/OODNotes/arrays.md");
    vectors = Path.of("src/test/Resources/ExampleDirectory/OODNotes/vectors.md");
    fake = Path.of("src/test/Resources/ExampleDirectory/OODNotes/fake.md");
    comp = new Modified();
  }

  /**
   * tests the modified time
   */
  @Test
  public void testModified() {
    try {
      Files.setLastModifiedTime(arrays, FileTime.fromMillis(1000));
      Files.setLastModifiedTime(vectors, FileTime.fromMillis(2));
    } catch (IOException e) {
      fail();
    }
    assertTrue(comp.compare(arrays, vectors) > 0);
    assertTrue(comp.compare(vectors, arrays) < 0);

  }

  /**
   * tests an exception
   */
  @Test
  public void testException() {
    assertThrows(
        RuntimeException.class,
        () -> comp.compare(arrays, fake));
    assertThrows(
        RuntimeException.class,
        () -> comp.compare(fake, vectors));
  }
}


