package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class to test the file walker
 */
class FileWalkerTest {
  String dir;
  Path badDir;
  Path directory;
  BasicFileAttributes da;


  /**
   * initializes the testing data
   */
  @BeforeEach
  public void initData() {
    dir = "src/test/Resources/ExampleDirectory/OODNotes";
    //badDir = Path.of("src/test/Resources/ExampleDirectory/Bad");
    directory = Path.of(dir);
    try {
      da = Files.readAttributes(directory, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException();
    }


  }

  /**
   * tests building a list of all valid Markdown paths in a directory
   */
  @Test
  public void testGetMarkdownPaths() {
    FileWalker mfv = new FileWalker();
    try {
      Files.walkFileTree(Path.of(dir), mfv);
    } catch (IOException e) {
      fail();
    }


    // build list of expected file paths
    ArrayList<Path> expectedFiles = new ArrayList<>();
    expectedFiles.add(Path.of(dir + "/arrays.md"));
    expectedFiles.add(Path.of(dir + "/io.md"));
    expectedFiles.add(Path.of(dir + "/vectors.md"));

    // get list of traversed Markdown file paths
    ArrayList<Path> actualFiles = mfv.getFiles("filename");

    // compare both lists
    assertEquals(3, actualFiles.size());
    assertArrayEquals(expectedFiles.toArray(), actualFiles.toArray());
/*
    assertThrows(
        RuntimeException.class,
        () -> Files.walkFileTree(badDir, mfv));

 */

    assertEquals(FileVisitResult.CONTINUE, mfv.visitFile(directory, da));
    assertThrows(
        RuntimeException.class,
        () -> mfv.visitFileFailed(directory, new IOException()));
  }

}