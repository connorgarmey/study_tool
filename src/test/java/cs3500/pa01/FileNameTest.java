package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the FileName Comparator and its methods
 */
class FileNameTest {
  Path arrays;
  Path vectors;
  Path io;
  Comparator<Path> comp;

  /**
   * initializes the data for testing
   */
  @BeforeEach
  public void initData() {
    arrays = Path.of("/src/test/Resources/ExampleDirectory/OODNotes/arrays.md");
    vectors = Path.of("/src/test/Resources/ExampleDirectory/OODNotes/vectors.md");
    io = Path.of("/src/test/Resources/ExampleDirectory/OODNotes/io.md");
    comp = new FileName();
  }

  /**
   * tests the filename comparator
   */
  @Test
  public void testFilename() {
    assertTrue(comp.compare(arrays, vectors) < 0);
    assertTrue(comp.compare(vectors, arrays) > 0);
    assertTrue(comp.compare(io, arrays) > 0);
    assertTrue(comp.compare(io, vectors) < 0);
  }
}