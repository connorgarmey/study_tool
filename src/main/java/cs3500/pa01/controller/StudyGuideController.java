package cs3500.pa01.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Controller for the study guide mode
 * of the program
 */
public class StudyGuideController implements Icontroller {
  String[] args;

  /**
   *  StudyGuideController constructor
   *
   * @param args the terminal input
   */
  public StudyGuideController(String[] args) {
    this.args = args;
    this.runApp();
  }

  /**
   * Central method for reading and writing the
   * study guide
   */
  @Override
  public void runApp() {
    Path from = Path.of(args[0]);
    File to = new File(args[2]);
    ArrayList<Path> files;
    FileWalker slay = new FileWalker();
    WriteFile wtf = new WriteFile();

    try {
      Files.walkFileTree(from, slay);
    } catch (IOException e) {
      System.out.println("Bad path");
    }

    files = slay.getFiles(args[1]);

    wtf.writeToFile(to, files);
  }
}
