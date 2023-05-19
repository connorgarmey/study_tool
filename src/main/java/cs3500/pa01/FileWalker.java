package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * Class that implements to file visitor to walk through
 * a file system
 */
public class FileWalker implements FileVisitor<Path> {
  ArrayList<Path> files = new ArrayList<>();


  /**
   * Called when a file is visited
   *
   * @param file
   *          a reference to the file
   *
   * @param attr
   *          the file's basic attributes
   *
   * @return a message to continue
   */
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    if (attr.isRegularFile()) {
      if (file.toString().endsWith(".md")) {
        files.add(file);
      }
    }
    return CONTINUE;
  }

  /**
   * Called after a directory is visited
   *
   * @param dir
   *          a reference to the directory
   *
   * @param exec
   *          {@code null} if the iteration of the directory completes without
   *          an error; otherwise the I/O exception that caused the iteration
   *          of the directory to complete prematurely
   *
   * @return a message to continue
   */
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    return CONTINUE;
  }

  /**
   * Called before a directory is visited
   *
   * @param dir
   *          a reference to the directory
   *
   * @param attrs
   *          the directory's basic attributes
   *
   * @return a message to continue
   */
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * Called if the file visit fails
   *
   * @param file
   *          a reference to the file
   *
   * @param exc
   *          the I/O exception that prevented the file from being visited
   *
   * @return a message to continue and the exception
   */
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    this.handler(exc);
    return CONTINUE;

  }

  /**
   * Returns an ordered list of files based on the given flag
   *
   * @param order the order flag command line argument
   *
   * @return a list of sorted file paths
   */
  public ArrayList<Path> getFiles(String order) {
    Sorter s = new Sorter();
    return s.getSortedFiles(files, order);
  }

  /**
   * Handles an exception from fileVisitFailed method
   *
   * @param exc IOException
   */
  private void handler(IOException exc) {
    System.err.println(exc);
    throw new RuntimeException();
  }
}





