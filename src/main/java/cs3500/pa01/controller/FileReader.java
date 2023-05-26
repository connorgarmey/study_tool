package cs3500.pa01.controller;

import cs3500.pa01.model.Filter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to represent a File Reader
 */
public class FileReader {


  /**
   * Reads the contents from a file to a String
   *
   * @param path A file object which corresponds to a path in the file system
   *             and some information at that path
   *
   * @return the contents of the file
   */
  public String readFromFile(Path path) {
    Scanner sc;
    try {
      sc = new Scanner(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    StringBuilder content = new StringBuilder();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      content.append(line).append("\n");
    }
    return content.toString();
  }


  /**
   * Goes through each file, reads it, and filters and formats the text
   *
   * @param files a list of all the file paths
   * @return all of the filtered text
   */
  public String[] readFiles(ArrayList<Path> files) {
    StringBuilder s = new StringBuilder();
    for (Path p : files) {
      s = new StringBuilder(s + this.readFromFile(p));
    }
    Filter f = new Filter();
    return f.drBracket(s.toString());
  }


}

