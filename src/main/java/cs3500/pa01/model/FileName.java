package cs3500.pa01.model;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * Comparator class that compares Paths by filename
 */
public class FileName implements Comparator<Path> {

  /**
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return int
   */
  @Override
  public int compare(Path o1, Path o2) {
    return o1.getFileName().compareTo(o2.getFileName());
  }
}
