package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class StudyGuideController implements IController {
  String[] args;
  StudyGuideController(String[] args) {
    this.args = args;
    this.runApp();
  }

  @Override
  public void runApp() {
    //Path from = Path.of(args[0]);
    File to = new File(args[2]);
    ArrayList<Path> files;
    FileWalker slay = new FileWalker();
    WriteFile wtf = new WriteFile();

    try {
      Files.walkFileTree(Path.of(args[0]), slay);
    } catch (IOException e) {
      throw new RuntimeException("Bad path");
    }

    files = slay.getFiles(args[1]);

    wtf.writeToFile(to, files);
  }
}
