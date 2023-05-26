package cs3500.pa01.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

/**
 * Comparator class to compare paths by creation time
 */
public class Created implements Comparator<Path> {

  /**
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return int
   */
  @Override
  public int compare(Path o1, Path o2) {
    BasicFileAttributes a1 = null;
    BasicFileAttributes a2 = null;

    try {
      a1 = Files.readAttributes(
          o1, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      a2 = Files.readAttributes(
          o2, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return a1.creationTime().compareTo(a2.creationTime());
  }
}
