package cs3500.pa01.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Class to write a file
 */
public class WriteFile {

  /**
   * writes the contents from files to a new one
   *
   * @param file the new file location
   * @param files the files to extract the contents
   */
  public void writeToFile(File file, ArrayList<Path> files) {
    FileReader fr = new FileReader();

    Path pathMd = file.toPath();
    String stringMd = file.toString();
    String stringSr = stringMd.substring(0, stringMd.length() - 2) + "sr";
    Path pathSr = Path.of(stringSr);

    this.writeHelp(pathMd, fr.readFiles(files)[0]);
    this.writeHelp(pathSr, fr.readFiles(files)[1]);
  }


  /**
   * Allows access to the private writeHelp method
   * in order to rewrite a file with updated content
   *
   * @param contents all contents as a String
   * @param path the path to write to
   */
  public void rewriteFile(String contents, Path path) {
    writeHelp(path, contents);
  }


  /**
   * Converts the data to be used for a file and
   * writes to that file path
   *
   * @param path the location to write to
   * @param contents the contents to write
   */
  private void writeHelp(Path path, String contents) {
    byte[] data = contents.getBytes();

    try {
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
