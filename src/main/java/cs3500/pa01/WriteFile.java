package cs3500.pa01;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Class to write a file
 */
public class WriteFile {
  String contents;

  /**
   * writes the contents from files to a new one
   *
   * @param file the new file location
   * @param files the files to extract the contents
   */
  public void writeToFile(File file, ArrayList<Path> files) {
    FileReader fr = new FileReader();
    contents = fr.readFiles(files);


    Path path = file.toPath();

    byte[] data = contents.getBytes();


    try {
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
