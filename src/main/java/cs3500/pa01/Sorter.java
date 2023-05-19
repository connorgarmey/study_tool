package cs3500.pa01;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class for sorting files
 */
public class Sorter {
  Comparator<Path> order;

  /**
   * Gets the order comparator based on the string provided
   *
   * @param o the ordering flag as a String
   */
  private void getOrder(String o) {
    order = switch (o) {
      case "filename" -> new FileName();
      case "created" -> new Created();
      case "modified" -> new Modified();
      default -> throw new IllegalArgumentException("Invalid order flag");
    };
  }

  /**
   * Sorts all files via the given list and order flag
   *
   * @param files a list of all the files
   *
   * @param o the ordering flag as a String
   *
   * @return an ordered list of files
   */
  public ArrayList<Path> getSortedFiles(ArrayList<Path> files, String o) {
    this.getOrder(o);
    files.sort(order);
    return files;
  }
}

